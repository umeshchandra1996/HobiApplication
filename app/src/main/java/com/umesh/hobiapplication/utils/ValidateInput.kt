package com.umesh.hobiapplication.utils

import android.content.Context
import java.util.regex.Pattern

class ValidateInput(private val appContext:Context) {

    /**
     * returns a email regular expression
     */
    private val emailRegex: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )



    /**
     * taking email as input and validate
     *
     * @param email
     * @return
     */
    fun isValidEmail(email: String): Boolean{
        if (emailRegex.matcher(email.trim()).matches())
            return true
        return false
    }
}