package com.gsv28rus.calendar

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gsv28rus.calendar.user.UserDao
import com.gsv28rus.calendar.user.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
