package com.example.legalassistant.models.server

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MessageResponse(
    val success: Boolean?,
    val response_text: String?
) : Parcelable