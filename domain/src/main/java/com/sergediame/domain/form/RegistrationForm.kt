package com.sergediame.domain.form


data class EmailAddressField(
    override val value: String,
    override val isOptional: Boolean = false
) : Field() {

    companion object {
        val EMPTY = EmailAddressField(value = "")
    }

    override val error: FormError?
        get() = if (validate()) null else FormError.InvalidEmailAddress

    override fun validate(): Boolean {
        return value.isNotEmpty() && value.validateEmail()
    }

}


data class RegistrationForm(
    val emailAddress: EmailAddressField,
    val password: PasswordField,
    val confirmPassword: PasswordField
) : Form {

    override val isValid: Boolean
        get() = emailAddress.isValid && password.isValid

    companion object {
        val INITIAL = RegistrationForm(
            emailAddress = EmailAddressField.EMPTY,
            password = PasswordField.EMPTY,
            confirmPassword = PasswordField.EMPTY
        )
    }
}