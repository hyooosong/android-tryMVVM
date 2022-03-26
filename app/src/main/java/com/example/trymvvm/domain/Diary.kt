package com.example.trymvvm.domain

import java.util.*

data class Diary(
    val id: String,
    val title: String,
    val content: String,
    val date: Date
)