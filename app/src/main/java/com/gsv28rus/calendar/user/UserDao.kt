package com.gsv28rus.calendar.user

import androidx.room.Dao
import androidx.room.Query
import com.gsv28rus.calendar.common.BaseDao

@Dao
abstract class UserDao : BaseDao<User>() {
    @Query("SELECT * from users")
    abstract fun getAll(): List<User>

    @Query("DELETE FROM users")
    abstract fun truncate()
}
