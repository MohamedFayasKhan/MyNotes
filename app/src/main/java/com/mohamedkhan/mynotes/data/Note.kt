package com.mohamedkhan.mynotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String?,
    var category: String,
    var description: String?,
    var createdTime: String,
    var modifiedTime: String
    ) {}