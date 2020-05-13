package com.wellfit.client.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.wellfit.client.api.graphql.AppSchema
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.content.defaultResource
import io.ktor.http.content.static
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import org.koin.ktor.ext.inject

@Suppress("unused")
fun Application.routes() {

    routing {
        val json: ObjectMapper by inject()

        graphql(AppSchema, json)

        authenticate {
            get("a") {
                call.respondText("1232")
            }
        }

        get("b") {
            call.respondText("1232")
        }

        static("/") {
            defaultResource("index.html")
        }
    }
}