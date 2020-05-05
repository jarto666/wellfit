package com.wellfit.client.api

import com.apurebase.kgraphql.schema.Schema
import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get

@KtorExperimentalLocationsAPI
@Location("/graphql")
data class GraphQLRequest(val query: String = "", val variables: Map<String, Any>? = emptyMap())

fun GraphQLErrors.asMap(): Map<String, Map<String, String>> {
    return mapOf("errors"
            to mapOf("message"
            to "Caught ${e.javaClass.simpleName}: ${e.message?.replace("\"", "")}")
    )
}

data class GraphQLErrors(val e: Exception)

@KtorExperimentalLocationsAPI
fun Route.graphql(mapper: ObjectMapper, schema: Schema) {

    post<GraphQLRequest> {
        val request = call.receive<GraphQLRequest>()

        val query = request.query
        //log.info("the graphql query: $query")

        val variables = mapper.writeValueAsString(request.variables)
        //log.info("the graphql variables: $variables")

        try {
            val result = schema.execute(query, variables)
            call.respondText(result)
        } catch (e: Exception) {
            call.respondText(mapper.writeValueAsString(GraphQLErrors(e).asMap()))
        }
    }
}