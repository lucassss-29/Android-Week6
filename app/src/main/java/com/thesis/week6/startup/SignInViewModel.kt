package com.thesis.week6.startup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thesis.week6.UserInfo.DataStore
import com.thesis.week6.UserInfo.User

class SignInViewModel : ViewModel() {
    val user = MutableLiveData<User>()
    val isSignInSucceed = MutableLiveData<User>()
    val errorMessage = MutableLiveData<String>()

    init {
        user.value = User()
    }

    fun login(){
        val dataStore = DataStore.instance
        dataStore.setLoginCallback(loginCallback)
        dataStore.login(user.value!!.email,user.value!!.password)
    }

    fun clear(){
        isSignInSucceed.value = null
        errorMessage.value = null
    }

    private val loginCallback  = object : DataStore.LoginCallback{
        override fun onSucceed(user: User) {
            this@SignInViewModel.isSignInSucceed.value = user
        }

        override fun onFailed(message: String) {
            errorMessage.value = message
        }
    }


}