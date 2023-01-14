package mainPackage.utils

import java.util.regex.Pattern

const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"



object utils1 {

    fun passwordCheck(password: String): Checks{
        var pattern = Pattern.compile(PASSWORD_PATTERN)
        var matcher = pattern.matcher(password)
        when(matcher.matches()) {
            true -> return Checks.PASSED
            false -> return Checks.INCORRECT_PASSWORD_FORM
        }
    }
}

enum class Checks {
    TEACHER, STUDENT, FAILED_CHECK, INCORRECT_PASSWORD_FORM, PASSWORD_DOESNT_MATCH, INCORRECT_EMAIL_FORM, PASSED, NEW_USER_CREATED
}