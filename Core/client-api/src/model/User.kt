package com.wellfit.client.api.model

open class User(
    val id: String,
    val name: String? = null,
    val age: Int? = null,
    val gender: Char? = null,
    val weight: Float = 0f, //kg
    val height: Float = 0f, //cm
    val measurementSystem: String = MeasurementSystem.metric)

class UserInput(id: String,
                name: String? = null,
                age: Int? = null,
                gender: Char? = null,
                weight: Float = 0f, //kg
                height: Float = 0f, //cm
                measurementSystem: String = MeasurementSystem.metric)
    : User(id, name, age, gender, weight, height, measurementSystem)