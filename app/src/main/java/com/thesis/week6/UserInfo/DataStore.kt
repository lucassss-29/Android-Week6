package com.thesis.week6.UserInfo

import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern

class DataStore private constructor() {
    private val userList = ArrayList<User>()
    private lateinit var loginCallback: LoginCallback
    private lateinit var signUpCallback: SignUpCallback

    companion object {
        val instance = DataStore()
    }


    fun signUp(fullName: String, email: String, password: String) {
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            signUpCallback.onFailed("Field cannot empty")
        }
        else if (!isEmailValid(email)){
            signUpCallback.onFailed("Your Email is invalid. Please try it again")
        }
        else if (!isValidPassword(password)){
            signUpCallback.onFailed("Your password is invalid. Please check it must contain at least 8, upper and special characters")
        }
        else {
            for (user in userList) {
                if (user.email == email) {
                    signUpCallback.onFailed("This email is already existed")
                    return
                }
            }
            val user = User(fullName, email, password)
            userList.add(user)
            signUpCallback.onSucceed()


        }
    }

    fun login(email: String, password: String) {
        for (user in userList) {
            if (user.email == email && user.password == password) {
                loginCallback.onSucceed(user)
                return
            }
        }
        loginCallback.onFailed("Cannot find any user")
    }

    fun isEmailValid(email : CharSequence?):Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String?) : Boolean{
        val pattern : Pattern
        val matcher : Matcher
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&*()])(?=\\S+\$).{8,}\$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }

    fun setSignUpCallback(signUpCallback: SignUpCallback) {
        this.signUpCallback = signUpCallback
    }

    fun setLoginCallback(loginCallback: LoginCallback) {
        this.loginCallback = loginCallback
    }

    interface LoginCallback {
        fun onSucceed(user: User)
        fun onFailed(message: String)

    }

    interface SignUpCallback {
        fun onSucceed()
        fun onFailed(message: String)
    }
}