package com.wellfit.client.api.graphql

import com.apurebase.kgraphql.Context
import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.wellfit.client.api.model.Gender
import com.wellfit.client.api.model.MeasurementSystem
import com.wellfit.client.api.model.User
import com.wellfit.client.api.model.UserInput
import com.wellfit.client.api.service.UserService
import org.litote.kmongo.fields

fun SchemaBuilder.userSchema(userService: UserService) {

    //region Types

    enum<MeasurementSystem> {
        value(MeasurementSystem.METRIC) {
            description = "KG/CM"
        }
        value(MeasurementSystem.IMPERIAL) {
            description = "LB/FT"
        }
    }

    enum<Gender>()
    type<User>()
    inputType<UserInput>()

    //endregion

    //region Queries

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

    //endregion

    //region Mutations

    authenticatedMutation("createUser") {

        resolver { user: UserInput ->
            val createdUser = userService.createUser(user)
            createdUser
        }
    }

    //endregion
}