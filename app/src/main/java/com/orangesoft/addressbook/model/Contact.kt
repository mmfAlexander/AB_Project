package com.orangesoft.addressbook.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    val id: Long = 0,
    val name: String = "",
    val phone: String,
    val phoneType: Int,
    val photoUri: String? = null
): Parcelable