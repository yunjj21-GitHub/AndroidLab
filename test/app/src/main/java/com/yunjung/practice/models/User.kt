package com.yunjung.practice.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var _id : String,
    var name : String,
    var age : Int,
    var address : String
):Parcelable
