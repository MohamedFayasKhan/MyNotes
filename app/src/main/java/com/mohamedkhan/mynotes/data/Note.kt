package com.mohamedkhan.mynotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val category: String,
    val description: String,
    val createdTime: String,
    val modifiedTime: String
    ) {}