package com.wellfit.client.api

import com.auth0.jwk.JwkProviderBuilder
import com.fasterxml.jackson.databind.SerializationFeature
import com.wellfit.client.api.di.mainModule
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.jackson.jackson
import io.ktor.locations.Locations
import io.ktor.util.KtorExperimentalAPI
import org.koin.core.context.startKoin
import org.koin.core.logger.PrintLogger
import java.net.URI
import java.util.concurrent.TimeUnit
import kotlin.collections.mapOf
import kotlin.collections.set

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    var configuration = Configuration()
    configuration.connectionStrings["mongo"] = environment.config.property("connectionStrings.mongo").getString()
    startKoin {
        // declare used logger
        logger(PrintLogger())
        properties(mapOf(
            "connectionString:mongo" to configuration.connectionStrings["mongo"]!!
        ))
        modules(mainModule)
    }

    install(Locations)

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header(HttpHeaders.XForwardedProto)
        anyHost()
        allowCredentials = true
        allowNonSimpleContentTypes = true

    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    installAuth()
}

private fun Application.installAuth() {

    //var s = System.getProperty("java.home") + "\\lib\\security\\cacerts"
    //System.setProperty("javax.net.ssl.trustStore", s);

    var jwtAudience = environment.config.property("jwt.audience").getString()
    var jwkIssuer = environment.config.property("jwt.issuer").getString()
    var jwks = environment.config.property("jwt.jwk").getString()

    val jwkProvider = JwkProviderBuilder(URI(jwks).toURL())
        .cached(10, 24, TimeUnit.HOURS)
        .rateLimited(10, 1, TimeUnit.MINUTES)
        .build()

    install(Authentication) {
        jwt {
            verifier(jwkProvider, jwkIssuer)
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
        }
    }
}