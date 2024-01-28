package com.sergediame.gozembusinesscase.map


import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
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

var locationCallback: LocationCallback? = null
var fusedLocationClient: FusedLocationProviderClient? = null
var locationRequired = false


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


    val context = LocalContext.current
    var currentLocation by remember {
        mutableStateOf(LocationDetails(0.toDouble(), 0.toDouble()))
    }
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (lo in p0.locations) {
                // Update UI with location data
                currentLocation = LocationDetails(lo.latitude, lo.longitude)
            }
        }
    }

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            locationRequired = true
            startLocationUpdates()
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        Modifier
            .padding(bottom = 48.dp)
            .fillMaxSize()) {

        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings
        ) {
            Marker(
                state = MarkerState(position = LatLng(content.lat, content.lng)),
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED),
                title = "Marker"
            )

            Marker(
                state = MarkerState(position = LatLng(currentLocation.lat, currentLocation.lng)),
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE),
                title = "My Location Marker"
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

        val permissions = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        Button(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(vertical = 16.dp),
            onClick = {
                if (permissions.all {
                        ContextCompat.checkSelfPermission(
                            context,
                            it
                        ) == PackageManager.PERMISSION_GRANTED
                    }) {
                    // Get the location
                    startLocationUpdates()
                } else {
                    launcherMultiplePermissions.launch(permissions)
                }
            }) {
            Text(text = "Get current location")
        }

        Text(text = "Latitude : " + currentLocation.lat)
        Text(text = "Longitude : " + currentLocation.lng)
    }
}


@SuppressLint("MissingPermission")
private fun startLocationUpdates() {
    locationCallback?.let {
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        fusedLocationClient?.requestLocationUpdates(
            locationRequest,
            it,
            Looper.getMainLooper()
        )
    }
}







