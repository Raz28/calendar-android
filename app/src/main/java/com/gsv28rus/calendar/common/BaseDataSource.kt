package ru.semejnayaapteka.app.model.common

import com.gsv28rus.calendar.common.ApiResponse
import io.reactivex.Single

interface BaseDataSource<T> {

    fun getList(offset: Int, limit: Int): Single<ApiResponse<List<T>>>
    fun getById(id: Int, cityId: Int): Single<ApiResponse<T>>

}