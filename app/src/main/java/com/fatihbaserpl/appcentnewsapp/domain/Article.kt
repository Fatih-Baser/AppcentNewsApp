package com.fatihbaserpl.appcentnewsapp.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "articles"
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,

    val title: String,
    val url: String,
    val urlToImage: String
):Parcelable