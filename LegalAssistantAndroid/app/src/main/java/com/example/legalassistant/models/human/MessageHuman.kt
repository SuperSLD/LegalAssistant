package com.example.legalassistant.models.human

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MessageHuman(
    val text: String?,
    val data: String?,
    val incoming: Boolean?
) : Parcelable