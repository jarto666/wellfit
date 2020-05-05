package com.wellfit.client.api.graphql

import com.apurebase.kgraphql.KGraphQL
import io.ktor.features.NotFoundException
import java.time.LocalDate

data class A (val a: Int, val b: Int)

class AppSchema() {

    val schema = KGraphQL.schema {

        configure {
            useDefaultPrettyPrinter = true
        }

        stringScalar<LocalDate> {
            serialize = { date -> date.toString() }
            deserialize = { dateString -> LocalDate.parse(dateString) }
        }

        query("sightings") {
            description = "Returns a subset of the UFO Sighting records"

            resolver { size: Int? -> listOf<UFOSighting>(UFOSighting(1)) }.withArgs {
                arg<Long> { name = "size"; defaultValue = 10; description = "The number of records to return" }
            }
        }

        query("sighting") {
            description = "Returns a single UFO Sighting record based on the id"

            resolver { id: Int -> UFOSighting(1) ?: throw NotFoundException("Sighting with id: $id does not exist") }
        }

        query("user") {
            description = "Returns a single User based on the id"

            resolver { id: Int -> users.getOrNull(id) ?: throw NotFoundException("User with id: $id does not exist") }
        }

        mutation("createUFOSighting") {
            description = "Adds a new UFO Sighting to the database"

            resolver { input: CreateUFOSightingInput -> 5+1 }
        }

        inputType<CreateUFOSightingInput>()

        type<UFOSighting> {
            description = "A UFO sighting"

            property(UFOSighting::date) {
                description = "The date of the sighting"
            }

            property<User>("user") {
                resolver { _ ->
                    users[(0..2).shuffled().last()]
                }
            }
        }

        type<CountrySightings> {
            description = "A country sighting; contains total number of occurrences"
        }

        type<User> {
            description = "A User who has reported a UFO sighting"

            property<UFOSighting?>("sighting") {
                resolver { user -> UFOSighting(1) }
            }
        }
    }

}