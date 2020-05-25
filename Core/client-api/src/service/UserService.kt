package com.wellfit.client.api.service

import com.wellfit.client.api.dao.UserRepository
import com.wellfit.client.api.model.User

class UserService(private val userRepository: UserRepository) {
    fun getUser(userId: String): User? {
        return userRepository.getUserById(userId)
    }

    fun createUser(user: User): User {
        return userRepository.createUser(user)
    }
}