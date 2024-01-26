package com.sergediame.domain

fun String.isNumeric(): Boolean {
    return this.toIntOrNull()?.let { true } ?: false
}

fun Double?.orZero(): Double {
    return this ?: 0.0
}