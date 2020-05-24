package com.wellfit.client.api.dao

import com.wellfit.client.api.model.User

interface UserRepository {
    fun getUserById(id: String): User?
    fun createUser(user: User): User
}