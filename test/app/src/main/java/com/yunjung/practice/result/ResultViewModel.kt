package com.yunjung.practice.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yunjung.practice.models.User

class ResultViewModel : ViewModel() {
    private var _user = MutableLiveData<User>()

    val user : LiveData<User>
        get() = _user

    fun updateUserValue(userInput : User){
        _user.value = userInput
    }
}