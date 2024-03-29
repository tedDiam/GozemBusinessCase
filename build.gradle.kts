// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
    id("com.google.gms.google-services") version "4.4.0" apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
}