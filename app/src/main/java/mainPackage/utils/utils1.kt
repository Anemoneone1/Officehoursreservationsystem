package mainPackage.utils

import java.util.regex.Pattern

const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"



object utils1 {

    fun passwordCheck(password: String): Boolean{
        var pattern = Pattern.compile(PASSWORD_PATTERN)
        var matcher = pattern.matcher(password)
        return matcher.matches()
    }
}