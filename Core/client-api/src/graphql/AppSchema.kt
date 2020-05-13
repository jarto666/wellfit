package com.wellfit.client.api.graphql

import com.apurebase.kgraphql.KGraphQL

data class Article(val id: Int, val text: String)

val AppSchema = KGraphQL.schema {

    authenticatedQuery("article", forRoles = listOf(UserType.ADMIN)) {
        resolver { id: Int?, text: String ->
            Article(id ?: -1, text)
        }
    }

    type<Article> {
        property<String>("text") {
            resolver { article: Article ->
                "${article.id}: ${article.text}"
            }
        }
    }
}