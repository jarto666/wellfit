package com.wellfit.client.api.model

class Goal(
    val userId: String,
    val caloriesPerDay: Int = 0,
    val weight: Float? = null //kg
)