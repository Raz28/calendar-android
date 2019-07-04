package com.gsv28rus.calendar.common

interface ServerErrorsHandler {
    fun handleError(serverErrors: ServerErrors)
}