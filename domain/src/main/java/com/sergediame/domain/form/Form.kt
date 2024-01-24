package com.sergediame.domain.form

interface Form {
    val isValid: Boolean
}

abstract class Field: Form {
    abstract val value: String
    abstract val error: FormError?
    abstract val isOptional: Boolean

    override val isValid: Boolean
        get() = value.isBlank() || error == null

    val hasError: Boolean
        get() = !(value.isBlank() || error == null)

    abstract fun validate(): Boolean
}

