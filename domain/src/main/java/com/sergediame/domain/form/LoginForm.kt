package com.sergediame.domain.form


private const val PASSWORD_MIN_LENGTH = 8

data class PasswordField(
    override val value: String,
    override val isOptional: Boolean = false
) : Field() {

    companion object {
        val EMPTY = PasswordField(value = "")
    }

    override val error: FormError?
        get() = if (validate()) null else FormError.PasswordTooShort(PASSWORD_MIN_LENGTH)

    override fun validate(): Boolean {
        return value.isNotEmpty() && value.validateMinChars(PASSWORD_MIN_LENGTH)
    }

}

data class LoginForm(
    val email: EmailAddressField,
    val password: PasswordField
) : Form {
    companion object {
        val INITIAL = LoginForm(EmailAddressField.EMPTY, PasswordField.EMPTY)
    }

    override val isValid: Boolean
        get() = email.isValid && password.isValid

}
