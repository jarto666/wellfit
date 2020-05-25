package com.wellfit.client.api.dao

import com.wellfit.client.api.model.User
import kotlin.test.Test

class UserRepositoryTests {

    private val users: UserRepository

    init {
        users = UserRepository(
            "mongodb://admin:Wellfit2020@ds035037.mlab.com:35037/wellfit?retryWrites=false",
            "wellfit")
    }

    @Test
    fun `add user test`() {
        val user = User("Test1", "Ilya Livshits", 24)
        users.createUser(user)
    }

    @Test
    fun `get user test`() {
        val user = users.getUser("Test1")
    }
}


