package com.gsv28rus.calendar.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: String,
    var phone: String? = null,
    var email: String? = null,
    val password: String? = null
)
