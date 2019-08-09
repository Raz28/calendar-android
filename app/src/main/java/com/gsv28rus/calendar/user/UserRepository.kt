package com.gsv28rus.calendar.user

interface UserRepository {
    fun insertUser(user: User)
    fun updateUser(user: User)
    fun deleteUser(user: User)
    fun getUser(id: String): User?
}
