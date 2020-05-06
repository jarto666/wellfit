package com.wellfit.client.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.wellfit.client.api.graphql.GraphQLHandler
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route

@KtorExperimentalLocationsAPI
@Location("/graphql")
data class GraphQLRequest(val query: String = "", val variables: Map<String, Any>? = emptyMap())
data class GraphQLErrors(val e: Exception)

fun GraphQLErrors.asMap(): Map<String, Map<String, String>> {
    return mapOf("errors"
            to mapOf("message"
            to "Caught ${e.javaClass.simpleName}: ${e.message?.replace("\"", "")}")
    )
}

@KtorExperimentalLocationsAPI
fun Route.graphql(handler: GraphQLHandler, mapper: ObjectMapper) {

    post<GraphQLRequest> {

        val request = call.receive<GraphQLRequest>()
        try {
            val result = handler.execute(request.query, request.variables ?: emptyMap())
            if (result.errors.isNotEmpty()) throw Exception(result.errors[0].message)
            call.respond(mapOf("data" to result.getData<Any>()))
        } catch (e: Exception) {
            call.respondText(mapper.writeValueAsString(GraphQLErrors(e).asMap()))
        }
    }
}