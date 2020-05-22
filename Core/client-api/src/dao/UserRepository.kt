package com.wellfit.client.api.dao

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.*

class UserRepository(val client: MongoClient = KMongo.createClient(), var databaseName: String = "wellfit") {

    //private var databaseName = "wellfit"

    constructor(connectionString: String) : this(KMongo.createClient(connectionString))

    constructor(connectionString: String, databaseName: String = "wellfit")
        : this(KMongo.createClient(connectionString), databaseName)

    val database: MongoDatabase get() = client.getDatabase("wellfit")
    val userCollection get() = database.getCollection<User>()

    fun getUser(id: String): User? {
        return userCollection.findOne {
            User::id eq id
        }
    }

    fun createUser(user: User) {
        userCollection.insertOne(user)
    }
}