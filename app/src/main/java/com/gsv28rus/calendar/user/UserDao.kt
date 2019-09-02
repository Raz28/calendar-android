package com.gsv28rus.calendar.user

import androidx.room.Dao
import androidx.room.Query
import com.gsv28rus.calendar.common.BaseDao

@Dao
abstract class UserDao : BaseDao<User>() {
    @Query("SELECT * FROM users LIMIT 1")
    abstract fun getCurrentUser(): User

    @Query("DELETE FROM users")
    abstract fun truncate()
}
