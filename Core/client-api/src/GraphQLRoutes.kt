package com.wellfit.client.api

import com.apurebase.kgraphql.context
import com.apurebase.kgraphql.schema.Schema
import com.fasterxml.jackson.databind.ObjectMapper
import com.wellfit.client.api.graphql.UnAuthorizedUserException
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.http.HttpStatusCode
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route

@KtorExperimentalLocationsAPI
@Location("/graphql")
data class GraphQLRequest(val query: String = "", val variables: Map<String, Any>? = emptyMap())

data class GraphQLError(val graphQLErrors: List<Map<String, String>>)

@KtorExperimentalLocationsAPI
fun Route.graphql(schema: Schema) {

    authenticate(optional = true) {
        post<GraphQLRequest> {

            try {
                val user = call.authentication.principal<JWTPrincipal>()
                val request = call.receive<GraphQLRequest>()
                val result = schema.execute(
                    request.query,
                    request.variables.toString(),
                    context {
                        if (user != null) +user
                    }

                )

                call.respond(result)
            } catch (e: Exception) {
                if (e is UnAuthorizedUserException) {
                    call.respond(HttpStatusCode.Unauthorized,
                        GraphQLError(listOf(mapOf("message" to "Unauthorized")))
                    )
                } else {
                    call.respond(GraphQLError(listOf(mapOf("message" to e.localizedMessage))))
                }
            }
        }
    }
}