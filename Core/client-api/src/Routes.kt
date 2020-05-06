package com.wellfit.client.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.wellfit.client.api.graphql.GraphQLHandler
import io.ktor.application.Application
import io.ktor.http.content.default
import io.ktor.http.content.static
import io.ktor.routing.routing
import org.koin.ktor.ext.inject

@Suppress("unused")
fun Application.routes() {

    routing {
        val qlHandler: GraphQLHandler by inject()
        val json: ObjectMapper by inject()

        graphql(qlHandler, json)

        static("/") {
            default("index.html")
        }
    }
}