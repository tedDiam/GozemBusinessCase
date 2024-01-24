package com.sergediame.domain.form

import com.sergediame.domain.isNumeric


/**
 * This function allows customization of validations absent in the library to validate the field [value]
 * @param function the lambda function that is called during validation.
 * It takes the field value which is a [String] as a parameter and expects a [Boolean] return value.
 */
internal fun String.validateCustom(function: (String) -> Boolean): Boolean {
    return function(this)
}

/**
 *This function validates an Email in [value]
 *It will return true if the string value is a valid email address.
 *The implementation makes use of the [android.util.Patterns] class to match the email address.
 */
internal fun String.validateEmail(): Boolean {
    val emailRegex = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)"
    return emailRegex.toRegex().matches(this)
}


/**
 *This function validates a Phone in [value]
 *It will return true if the string value is a valid phone number.
 *The implementation makes use of the [android.util.Patterns] class to match the phone number.
 */
internal fun String.validatePhone(): Boolean {
    val phoneRegex = "(\\+[0-9]+)?(\\([0-9]+\\)[\\- ]*)?([0-9][0-9\\- ]+[0-9])"
    return phoneRegex.toRegex().matches(this)
}

/**
 *This function validates a Web Url in [value]
 *It will return true if the string value is a valid web url.
 */
internal fun String.validateWebUrl(): Boolean {
    val webUrlRegex =
        "(https?://)?(www\\.)?[-a-zA-Z0-9@:%._+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_+~#?&/=]*)"

    return webUrlRegex.toRegex().matches(this)
}

/**
 * This function validates a Card Number in [value]
 * It will return true if the string value is a valid card number. This function makes use of the [Luhn Algorithm](https://www.creditcardvalidator.org/articles/luhn-algorithm) to verify the validity of the credit cards.
 */
internal fun String.validateCardNumber(): Boolean {
    var checksum = 0

    for (i in this.length - 1 downTo 0 step 2) {
        checksum += this[i] - '0'
    }
    for (i in this.length - 2 downTo 0 step 2) {
        val n: Int = (this[i] - '0') * 2
        checksum += if (n > 9) n - 9 else n
    }

    val valid = checksum%10 == 0

    return valid
}

/**
 * This function validates a required field.
 * It will return true if the value is not empty.
 */
internal fun String.validateRequired(): Boolean {
    return this.isNotEmpty()
}

/**
 * A function that checks the upper limit i.e. maximum number of characters in [value].
 * It will return true if the characters are lesser than or equal to the specified limit.
 * @param limit the maximum characters that [value] can hold.
 * @param message the error message passed to [showError] to display if the characters are greater than the limit.
 */
internal fun String.validateMaxChars(limit: Int): Boolean {
    return this.length <= limit
}

/**
 * A function that checks the lower limit i.e the least number of characters in [value].
 * It will return true if the characters are greater than or equal to the specified limit.
 * @param limit the least number of characters that [value] can hold.
 */
internal fun String.validateMinChars(limit: Int): Boolean {
    return this.length >= limit
}


/**
 * A function that checks the lower limit i.e the least numeric value in [value] when transformed to [Int] by [transform].
 * It will return true if the numeric value is greater than or equal to the specified limit.
 * @param limit the least numeric value that [value] can hold.
 */
internal fun String.validateMinValue(limit: Int): Boolean {
    return this.isNumeric() && this.toDouble() >= limit
}

/**
 * A function that checks the upper limit i.e. maximum numeric value in [value] when transformed to [Int] by [transform].
 * It will return true if the numeric value is lesser than or equal to the specified limit.
 * @param limit the greatest numeric value that [value] can hold.
 */
internal fun String.validateMaxValue(limit: Int): Boolean {
    return this.isNumeric() && this.toDouble() <= limit
}