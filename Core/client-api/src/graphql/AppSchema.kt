package com.wellfit.client.api.graphql

import com.expediagroup.graphql.annotations.GraphQLDescription

data class Widget(val id: Int, val value: String)

class WidgetService {
    @GraphQLDescription("sample")
    fun widgetById(id: Int): Widget? {
        return Widget(id, "111")
    }

    @Deprecated("Use widgetById")
    fun widgetByValue(value: String): Widget? {
        return Widget(1, value)
    }
}

class WidgetUpdater {
    fun saveWidget(value: String): Widget? {
        return Widget(1, value)
    }
}