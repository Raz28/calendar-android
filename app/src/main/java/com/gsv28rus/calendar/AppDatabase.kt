package com.gsv28rus.calendar

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gsv28rus.calendar.dao.UserDao
import com.gsv28rus.calendar.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
