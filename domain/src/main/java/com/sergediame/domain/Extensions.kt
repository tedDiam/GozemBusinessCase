package com.sergediame.domain

fun String.isNumeric(): Boolean {
    return this.toIntOrNull()?.let { true } ?: false
}