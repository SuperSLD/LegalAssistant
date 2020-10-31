package com.example.legalassistant.models.human

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SolutionHuman(
    val title: String?,
    val subtitle: String?,
    val link: String?,
    val pdf: String?,
    val text: String?

) : Parcelable

