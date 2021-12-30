package com.yunjung.practice.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // 클래스 내부에서 접근할 데이터 (수정 가능)
    private val _value = MutableLiveData<Int>(0)

    // 클래스 외부에서 접근할 데이터 (수정 불가능)
    val value : LiveData<Int>
        get() = _value // 클래스 외부에서 접근할때엔 해당 변수로 접근해 내부에서 사용하는 변수를 get으로 반환

    // 데이터를 갱신할 때 사용할 메소드
    fun updateValue(){

    }
}