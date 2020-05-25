package com.wellfit.client.api.graphql

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.apurebase.kgraphql.schema.dsl.operations.AbstractOperationDSL
import com.apurebase.kgraphql.schema.dsl.operations.MutationDSL
import com.apurebase.kgraphql.schema.dsl.operations.QueryDSL
import io.ktor.auth.jwt.JWTPrincipal

class UserRole private constructor() {
    companion object {
        val ADMIN = "admin"
        val USER = "user"
    }
}

fun SchemaBuilder.authenticatedQuery(
    name: String,
    forRoles: List<String> = emptyList(),
    init: QueryDSL.() -> Unit
) = query(name) {
    authenticate(forRoles)
    init()
}

fun SchemaBuilder.authenticatedMutation(
    name: String,
    forRoles: List<String> = emptyList(),
    init: MutationDSL.() -> Unit
) = mutation(name) {
    authenticate(forRoles)
    init()
}

@Throws(InvalidActionForRoleException::class, UnAuthorizedUserException::class)
private fun AbstractOperationDSL.authenticate(forRoles: List<String>) = accessRule {
    val user = it.get<JWTPrincipal>()
        ?: return@accessRule UnAuthorizedUserException()
    val userRoles = user.payload.getClaim("roles")?.asString()?.split(',') ?: emptyList()
    if (!userRoles.contains(UserRole.ADMIN) && forRoles.isNotEmpty() && !userRoles.any {it in forRoles}) {
        return@accessRule InvalidActionForRoleException()
    }
    null
}

class InvalidActionForRoleException : Exception()
class UnAuthorizedUserException : Exception()