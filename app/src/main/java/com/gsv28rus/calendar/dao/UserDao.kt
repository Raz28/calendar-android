package com.gsv28rus.calendar.dao

import androidx.room.Dao
import androidx.room.Query
import com.gsv28rus.calendar.common.BaseDao
import com.gsv28rus.calendar.model.User

@Dao
abstract class UserDao : BaseDao<User>() {
    @Query("SELECT * from users")
    abstract fun getAll(): List<User>

    @Query("DELETE FROM users")
    abstract fun truncate()
}
