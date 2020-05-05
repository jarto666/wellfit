package com.wellfit.client.api.graphql

import java.time.LocalDate

val users = listOf(
    User(id = 1, name = "Amber"),
    User(id = 2, name = "Greg"),
    User(id = 3, name = "Frank"))

data class User(val id: Int = -1, val name: String = "")

data class CountrySightings(var numOccurrences: Int = 0) {

    var state: String = ""
        get() = field.toUpperCase()

    var country: String = ""
        get() = field.toUpperCase()

}

data class UFOSighting(
    var id: Int = -1,
    var date: LocalDate = LocalDate.now(),
    var city: String? = "",
    var state: String? = "",
    var country: String? = "",
    var shape: String? = "",
    var duration: Double = 0.0,
    var comments: String? = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
)

fun CreateUFOSightingInput.toUFOSighting() : UFOSighting {
    return UFOSighting(
        date = this.date ?: LocalDate.now(),
        city = this.city,
        state = this.state,
        country = this.country,
        shape = this.shape,
        duration = this.duration ?: 0.0,
        comments = this.comments,
        latitude = this.latitude ?: 0.0,
        longitude = this.longitude ?: 0.0
    )
}

data class CreateUFOSightingInput(
    var date: LocalDate? = LocalDate.now(),
    var city: String? = "",
    var state: String? = "",
    var country: String? = "",
    var shape: String? = "",
    var duration: Double? = 0.0,
    var comments: String? = "",
    var latitude: Double? = 0.0,
    var longitude: Double? = 0.0
)