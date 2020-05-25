package com.wellfit.client.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.wellfit.client.api.graphql.AppSchema
import io.ktor.application.Application
import io.ktor.http.content.defaultResource
import io.ktor.http.content.static
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.routing.routing
import org.koin.ktor.ext.inject

@KtorExperimentalLocationsAPI
@Suppress("unused")
fun Application.routes() {

    routing {
        val json: ObjectMapper by inject()
        val appSchema: AppSchema by inject()

        graphql(appSchema.schema)

        static("/") {
            defaultResource("index.html")
        }
    }
}