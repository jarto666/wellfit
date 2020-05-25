package com.wellfit.client.api.model

open class User(
    val id: String,
    val name: String? = null,
    val age: Int? = null,
    val gender: Gender? = null,
    val weight: Float = 0f, //kg
    val height: Float = 0f, //cm
    val measurementSystem: MeasurementSystem = MeasurementSystem.METRIC)

class UserInput(id: String,
                name: String? = null,
                age: Int? = null,
                gender: Gender? = null,
                weight: Float = 0f, //kg
                height: Float = 0f, //cm
                measurementSystem: MeasurementSystem = MeasurementSystem.METRIC)
    : User(id, name, age, gender, weight, height, measurementSystem)