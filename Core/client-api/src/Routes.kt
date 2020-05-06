package com.wellfit.client.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.wellfit.client.api.graphql.GraphQLHandler
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.content.*
import io.ktor.http.push
import io.ktor.routing.get
import io.ktor.routing.routing
import org.koin.ktor.ext.inject
import java.io.File

@Suppress("unused")
fun Application.routes() {

    routing {
        val qlHandler: GraphQLHandler by inject()
        val json: ObjectMapper by inject()

        graphql(qlHandler, json)


        static("/") {
            defaultResource("index.html")
//            staticRootFolder = File("files")
//
//            default("index.html")
        }
    }
}