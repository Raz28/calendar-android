package com.gsv28rus.calendar.common

data class ApiResponse<out T>(
        val data: T,
        val message: String
)