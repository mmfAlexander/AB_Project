package com.orangesoft.addressbook.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Phone(
    val number: String,
    val type: Int
) : Parcelable