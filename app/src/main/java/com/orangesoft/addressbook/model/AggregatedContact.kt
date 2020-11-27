package com.orangesoft.addressbook.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AggregatedContact(
    val name: String = "",
    val mainPhone: String,
    val phones: List<Phone>,
    val photoUri: String?
) : Parcelable