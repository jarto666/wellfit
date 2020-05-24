package com.wellfit.client.api.dao

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import com.wellfit.client.api.model.User
import org.litote.kmongo.*

class MongoUserRepository(
    val client: MongoClient = KMongo.createClient(),
    var databaseName: String = "wellfit"): UserRepository {

    //private var databaseName = "wellfit"

    constructor(connectionString: String) : this(KMongo.createClient(connectionString))

    constructor(connectionString: String, databaseName: String = "wellfit")
        : this(KMongo.createClient(connectionString), databaseName)

    val database: MongoDatabase get() = client.getDatabase("wellfit")
    val userCollection get() = database.getCollection<User>()

    override fun getUserById(id: String): User? {
        return userCollection.findOne {
            User::id eq id
        }
    }

    override fun createUser(user: User): User {
        userCollection.insertOne(user)
        return user
    }
}