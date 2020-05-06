package com.wellfit.client.api.di

import com.expediagroup.graphql.SchemaGeneratorConfig
import com.expediagroup.graphql.TopLevelObject
import com.expediagroup.graphql.toSchema
import com.fasterxml.jackson.databind.ObjectMapper
import com.wellfit.client.api.graphql.GraphQLHandler
import com.wellfit.client.api.graphql.WidgetService
import com.wellfit.client.api.graphql.WidgetUpdater
import org.koin.dsl.module

fun getQlHandler(): GraphQLHandler {
    val config = SchemaGeneratorConfig(supportedPackages = listOf("com.wellfit"))
    val queries = listOf(TopLevelObject(WidgetService()))
    val mutations = listOf(TopLevelObject(WidgetUpdater()))

    val schema = toSchema(config, queries, mutations)
    return GraphQLHandler(schema)
}

val mainModule = module  {
    single { ObjectMapper() }
    single { getQlHandler() }
}