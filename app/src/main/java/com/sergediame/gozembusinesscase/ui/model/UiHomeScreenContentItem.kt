package com.sergediame.gozembusinesscase.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dataset
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.sergediame.domain.entity.Content
import com.sergediame.domain.entity.HomeScreenContentItem
import com.sergediame.gozembusinesscase.navigation.Graph


private const val PROFILE_SECTION = "profile"
private const val MAP_SECTION = "map"
private const val DATA_SECTION = "data"


data class UiHomeScreenContentItem(
    val content: UiContent,
    val type: String
) {
    companion object{
        val EMPTY = UiHomeScreenContentItem(content = UiContent.EMPTY, type = "")
    }
}

data class UiContent(
    val email: String,
    val image: String,
    val lat: Double,
    val lng: Double,
    val name: String,
    val pin: String,
    val source: String,
    val title: String,
    val value: String,
    val icon: ImageVector,
    val color: Color,
    val route:String
){
    companion object {
        val EMPTY = UiContent(
            email = "",
            image = "",
            lat = 0.0,
            lng = 0.0,
            name = "",
            pin = "",
            source = "",
            title = "",
            value = "",
            icon = Icons.Filled.Menu,
            color = Color.Black,
            route = ""
        )
    }
}


fun HomeScreenContentItem.toUi() = UiHomeScreenContentItem(
    content = content.toUi(type),
    type = type
)


fun Content.toUi(type: String) = UiContent(
    email = email,
    image = image,
    lat = lat,
    lng = lng,
    name = name,
    pin = pin,
    source = source,
    title = title,
    value = value,
    icon = attachIcon(type),
    color = attachColor(type),
    route = attachRoute(type)
)


private fun attachIcon(type: String):ImageVector{
    return when(type){
        PROFILE_SECTION -> Icons.Filled.Person
        MAP_SECTION -> Icons.Filled.Map
        DATA_SECTION -> Icons.Filled.Dataset
        else -> Icons.Filled.Menu
    }
}


private fun attachColor(type: String):Color{
    return when(type){
        PROFILE_SECTION -> Color(0xFF264a91)
        MAP_SECTION -> Color(0xFF912680)
        DATA_SECTION -> Color(0xFF916c26)
        else -> Color(0xFF264a91)
    }
}

private fun attachRoute(type: String):String{
    return when(type){
        PROFILE_SECTION -> Graph.PROFILE
        MAP_SECTION -> Graph.MAP
        DATA_SECTION -> Graph.DATA
        else -> Graph.PROFILE
    }
}
