package com.wellfit.client.api.graphql

import com.apurebase.kgraphql.Context
import com.auth0.jwt.interfaces.Payload
import io.ktor.auth.jwt.JWTPrincipal

val Context.user: Payload
    get() = get<JWTPrincipal>()!!.payload

val Context.userId: String
    get() = user.getClaim("user_id").asString()