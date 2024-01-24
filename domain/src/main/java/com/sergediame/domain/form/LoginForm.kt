package com.sergediame.domain.form


private const val PASSWORD_MIN_LENGTH = 8
private const val DEFAULT_PHONE_CODE = "+225"

data class PhoneNumberField(
    override val value: String,
    val phoneCode: String = DEFAULT_PHONE_CODE,
    override val isOptional: Boolean = false
) : Field() {
    companion object {
        val EMPTY = PhoneNumberField(value = "", phoneCode = "")
    }

    override val error: FormError?
        get() = if (validate()) null else FormError.InvalidPhoneNumber

    override fun validate(): Boolean {

        return value.isNotEmpty() && value.validatePhone()
    }

    fun getFullPhoneNumber(): String {
        return phoneCode.plus(value)
    }

}


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
    val phoneNumber: PhoneNumberField,
    val password: PasswordField
) : Form {
    companion object {
        val INITIAL = LoginForm(PhoneNumberField.EMPTY, PasswordField.EMPTY)
    }

    override val isValid: Boolean
        get() = phoneNumber.isValid && password.isValid

}
