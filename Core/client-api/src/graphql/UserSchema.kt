package com.wellfit.client.api.graphql

import com.apurebase.kgraphql.Context
import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.wellfit.client.api.model.User
import com.wellfit.client.api.model.UserInput
import com.wellfit.client.api.service.UserService
import org.litote.kmongo.fields

fun SchemaBuilder.userSchema(userService: UserService) {

    type<User> {
        property(User::measurementSystem) {
            description = "Two possible values: 'metric' and 'imperial'"
        }
    }
    inputType<UserInput> {

    }

    authenticatedQuery("user") {

        description = "Get current user information"

        resolver { ctx: Context ->
            userService.getUser(ctx.userId)
        }
    }

    authenticatedQuery("userById") {
        resolver { id: String ->
            userService.getUser(id)
        }
    }

    authenticatedMutation("createUser") {

        resolver { user: UserInput ->
            val createdUser = userService.createUser(user)
            createdUser
        }
    }
}