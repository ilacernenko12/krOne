package com.example.krone.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val header: String,
    val body: String,
    val isPinned: Boolean = false
)
