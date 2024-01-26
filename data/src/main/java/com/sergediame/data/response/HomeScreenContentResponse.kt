package com.sergediame.data.response

import com.sergediame.domain.entity.Content
import com.sergediame.domain.entity.HomeScreenContentItem
import com.sergediame.domain.orZero


import kotlinx.serialization.Serializable

@Serializable
class HomeScreenContentResponse : ArrayList<HomeScreenContentItemResponse>()


@Serializable
data class HomeScreenContentItemResponse(
    val content: ContentResponse? = null,
    val type: String? = null
)

@Serializable
data class ContentResponse(
    val email: String? = null,
    val image: String? = null,
    val lat: Double? = null,
    val lng: Double? = null,
    val name: String? = null,
    val pin: String? = null,
    val source: String? = null,
    val title: String? = null,
    val value: String? = null
)

fun HomeScreenContentItemResponse.toDomain() = HomeScreenContentItem(
    content = content?.toDomain() ?: Content.EMPTY,
    type = type.orEmpty()
)


fun ContentResponse.toDomain() = Content(
    email = email.orEmpty(),
    image = image.orEmpty(),
    lat = lat.orZero(),
    lng = lng.orZero(),
    name = name.orEmpty(),
    pin = pin.orEmpty(),
    source = source.orEmpty(),
    title = title.orEmpty(),
    value = value.orEmpty()
)

const val MOCKED_HOME_SCREEN_CONTENT_RESPONSE = """[
  {
    "type": "profile",
    "content": {
      "image": "https://some.server/profile_image_url.png",
      "name": "John Doe",
      "email": "john@domain.com"
    }
  },
  {
    "type": "map",
    "content": {
      "title": "Location",
      "pin": "https://some.server/map_pin_image_url.png",
      "lat": 1.2345,
      "lng": 1.2345
    }
  },
  {
    "type": "data",
    "content": {
      "title": "Information",
      "source": "wss://echo.websocket.org",
      "value": "Loading..."
    }
  }
]"""





