package com.yunjung.practice.three

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yunjung.practice.models.User

class ThreeViewModel : ViewModel() {
    private val _userList = MutableLiveData<List<User>>()

    val userList : LiveData<List<User>>
        get() = _userList

    init {
        val tmpList = ArrayList<User>()

        tmpList.add(User("1", "유기현",30, "경기도 고양시"))
        tmpList.add(User("2","손현우",31, "서울특별시 도봉구"))
        tmpList.add(User("3","임창균",27, "광주광역시 광산구"))
        tmpList.add(User("4","이민혁", 30, "서울특별시 종로구"))
        tmpList.add(User("5","채형원",30, "광주광역시 광산구"))
        tmpList.add(User("6","이주헌",29, "서울특별시 서초구"))

        _userList.value = tmpList
    }
}