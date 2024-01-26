package com.sergediame.domain.entity


data class HomeScreenContentItem(
    val content: Content,
    val type: String
) {
    companion object{
        val EMPTY = HomeScreenContentItem(content = Content.EMPTY, type = "")
    }
}

data class Content(
    val email: String,
    val image: String,
    val lat: Double,
    val lng: Double,
    val name: String,
    val pin: String,
    val source: String,
    val title: String,
    val value: String
){
    companion object {
        val EMPTY = Content(
            email = "",
            image = "",
            lat = 0.0,
            lng = 0.0,
            name = "",
            pin = "",
            source = "",
            title = "",
            value = ""
        )
    }
}

