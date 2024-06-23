package com.mohamedkhan.mynotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note (
    @PrimaryKey(autoGenerate = true)
    private val id: Int,
    private val title: String,
    private val category: String,
    private val description: String,
    private val createdTime: String,
    private val modifiedTime: String
    ) {}