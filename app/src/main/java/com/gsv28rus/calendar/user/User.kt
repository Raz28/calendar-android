package com.gsv28rus.calendar.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey var id: Int = 0,
    var firstname: String? = null,
    var lastname: String? = null,
    var surname: String? = null,
    var phone: String? = null,
    var email: String? = null
)
