package com.example.legalassistant.models.server

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SolutionResponse(
        val id: String?,
        val title: String?,
        val subtitle: String?,
        val pdf: String?,
        val link: String?,
        val text: String?
): Parcelable