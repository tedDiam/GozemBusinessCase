package com.sergediame.gozembusinesscase.ui.theme

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

interface Dimensions {
    val tooSmall: Dp
    val extraSmall: Dp
    val small: Dp
    val normal: Dp
    val large: Dp
    val extraLarge: Dp
    val normalButtonHeight: Dp
    val minButtonWidth: Dp
    val toolbarIconPadding: Dp
    val toolbarIconSize: Dp
}

val portraitDimensions: Dimensions = object : Dimensions {
    override val tooSmall: Dp
        get() = 2.dp
    override val extraSmall: Dp
        get() = 4.dp
    override val small: Dp
        get() = 8.dp
    override val normal: Dp
        get() = 16.dp
    override val large: Dp
        get() = 24.dp
    override val extraLarge: Dp
        get() = 32.dp
    override val normalButtonHeight: Dp
        get() = 56.dp
    override val minButtonWidth: Dp
        get() = 120.dp
    override val toolbarIconPadding: Dp
        get() = 12.dp
    override val toolbarIconSize: Dp
        get() = 32.dp
}

val landScapeDimensions :Dimensions = object : Dimensions {
    override val tooSmall: Dp
        get() = TODO("Not yet implemented")
    override val extraSmall: Dp
        get() = TODO("Not yet implemented")
    override val small: Dp
        get() = TODO("Not yet implemented")
    override val normal: Dp
        get() = TODO("Not yet implemented")
    override val large: Dp
        get() = TODO("Not yet implemented")
    override val extraLarge: Dp
        get() = TODO("Not yet implemented")
    override val normalButtonHeight: Dp
        get() = TODO("Not yet implemented")
    override val minButtonWidth: Dp
        get() = TODO("Not yet implemented")
    override val toolbarIconPadding: Dp
        get() = TODO("Not yet implemented")
    override val toolbarIconSize: Dp
        get() = TODO("Not yet implemented")

}

@Composable
fun getDimens():Dimensions {

    val activity = LocalContext.current as Activity

    return when(activity.requestedOrientation){
        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE -> landScapeDimensions
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT -> portraitDimensions
        else -> portraitDimensions
    }
}

