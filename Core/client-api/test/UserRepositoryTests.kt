package com.wellfit.client.api.dao

import kotlin.test.Test

class UserRepositoryTests {

    private val users: UserRepository

    init {
        users = UserRepository(
            "mongodb://admin:Wellfit2020@ds035037.mlab.com:35037/wellfit",
            "wellfit")
    }

    @Test
    fun `add user test`() {
        val user = User("TESTID1", "123", 2)
        users.createUser(user)
    }

    @Test
    fun `get user test`() {
        val user = users.getUser("TESTID1")
    }
}


