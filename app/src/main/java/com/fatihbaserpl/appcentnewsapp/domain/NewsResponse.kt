package com.fatihbaserpl.appcentnewsapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResponse(
    val articles: List<Article>,
    val totalResults: Int
):Parcelable

