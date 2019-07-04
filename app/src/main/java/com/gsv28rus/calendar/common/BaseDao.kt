package com.gsv28rus.calendar.common

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

abstract class BaseDao<in T> {

    /**
     * Insert an array of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg obj: T)

    /**
     * Update an array of objects in the database.
     *
     * @param obj the object to be updated
     */
    @Update
    abstract fun update(vararg obj: T)

    /**
     * Delete an array of objects in the database.
     *
     * @param obj the object to be deleted
     */
    @Delete
    abstract fun delete(vararg obj: T)

}