package com.wellfit.client.api.di

import com.fasterxml.jackson.databind.ObjectMapper
import com.mongodb.client.MongoClient
import com.wellfit.client.api.dao.UserRepository
import com.wellfit.client.api.graphql.AppSchema
import org.koin.dsl.module
import org.litote.kmongo.KMongo
import com.wellfit.client.api.Configuration
import com.wellfit.client.api.dao.MongoUserRepository
import com.wellfit.client.api.service.UserService

val mainModule = module  {
    single<MongoClient> { KMongo.createClient(getProperty("connectionString:mongo") as String) }
    single<ObjectMapper> { ObjectMapper() }
    single<AppSchema> { AppSchema(get()) }
    factory<UserRepository> { MongoUserRepository(get<MongoClient>(), "wellfit") }
    factory<UserService> { UserService(get()) }
}