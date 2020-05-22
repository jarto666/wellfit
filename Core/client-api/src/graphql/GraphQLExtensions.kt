package com.wellfit.client.api.graphql

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.apurebase.kgraphql.schema.dsl.operations.AbstractOperationDSL
import com.apurebase.kgraphql.schema.dsl.operations.MutationDSL
import com.apurebase.kgraphql.schema.dsl.operations.QueryDSL
import io.ktor.auth.jwt.JWTPrincipal

enum class UserType {
    ADMIN, NON_ADMIN
}

fun SchemaBuilder.authenticatedQuery(
    name: String,
    forRoles: List<UserType>,
    init: QueryDSL.() -> Unit
) = query(name) {
    authenticate(forRoles)
    init()
}

fun SchemaBuilder.authenticatedMutation(
    name: String,
    forRoles: List<UserType>,
    init: MutationDSL.() -> Unit
) = mutation(name) {
    authenticate(forRoles)
    init()
}

@Throws(InvalidActionForRoleException::class, UnAuthorizedUserException::class)
private fun AbstractOperationDSL.authenticate(forRoles: List<UserType>) = accessRule {
    val user = it.get<JWTPrincipal>()
        ?: return@accessRule UnAuthorizedUserException()
//    if (!user.payload.getClaim("roles").map { role -> forRoles.contains(role) }.reduce { acc, b -> acc && b }) {
//        return@accessRule InvalidActionForRoleException()
//    }
    null
}

class InvalidActionForRoleException : Exception()
class UnAuthorizedUserException : Exception()