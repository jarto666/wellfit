package com.wellfit.client.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.wellfit.client.api.graphql.AppSchema
import io.ktor.application.Application
import io.ktor.http.content.default
import io.ktor.http.content.static
import io.ktor.routing.get
import io.ktor.routing.routing
import org.koin.experimental.property.inject
import org.koin.ktor.ext.inject

@Suppress("unused")
fun Application.routes() {

    routing {
        val appSchema: AppSchema by inject()
        val json: ObjectMapper by inject()

        graphql(json, appSchema.schema)

        static("/") {
            default("index.html")
        }
    }
}