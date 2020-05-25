package com.wellfit.client.api.graphql

import com.apurebase.kgraphql.Context
import com.apurebase.kgraphql.KGraphQL
import com.auth0.jwt.interfaces.Payload
import com.wellfit.client.api.dao.UserRepository
import com.wellfit.client.api.model.User
import com.wellfit.client.api.service.UserService
import io.ktor.auth.jwt.JWTPrincipal
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.java.KoinJavaComponent.get

class AppSchema(
    val userService: UserService
) : KoinComponent  {
    val schema = KGraphQL.schema {
        stringScalar<Char> {
            deserialize = { str: String -> str[0] }
            serialize = Char::toString
        }

        userSchema(userService)
    }
}