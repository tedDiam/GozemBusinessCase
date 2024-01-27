package com.sergediame.gozembusinesscase.map


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun MapRoute(map: UiMap) {
    MapScreen(uiState = map)
}

@Composable
fun MapScreen(uiState: UiMap) {
    MapContent(content = uiState)
}

@Composable
fun MapContent(content: UiMap) {
    val location = LatLng(content.lat, content.lng)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 4f)
    }

    var uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = true)) }
    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.TERRAIN))
    }

    Box(Modifier.padding(bottom = 48.dp).fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings
        ){
            Marker(
                state = MarkerState(position = LatLng(content.lat, content.lng)),
                title = "Marker"
            )
        }
        Switch(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 16.dp),
            checked = uiSettings.zoomControlsEnabled,
            onCheckedChange = {
                uiSettings = uiSettings.copy(zoomControlsEnabled = it)
                properties = if (it) {
                    properties.copy(mapType = MapType.TERRAIN)
                } else {
                    properties.copy(mapType = MapType.SATELLITE)
                }
            }
        )
    }
}





