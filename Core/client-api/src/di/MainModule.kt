package com.wellfit.client.api.di

import com.fasterxml.jackson.databind.ObjectMapper
import com.wellfit.client.api.graphql.AppSchema
import org.koin.dsl.module

val mainModule = module  {
    single { ObjectMapper() }
    single { AppSchema }
}