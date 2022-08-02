package com.needcode.rangkirku.database.persistence

import androidx.lifecycle.LiveData
import androidx.room.*

//Data Access Object
@Dao
interface WaybillDao {

    //ketika insert datanya sama, maka akan di replace
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(waybillEntity: WaybillEntity)

    @Update
    suspend fun update(waybillEntity: WaybillEntity)

    @Delete
    suspend fun delete(waybillEntity: WaybillEntity)

    @Query("SELECT * FROM tableWaybill")
    fun select(): LiveData<List<WaybillEntity>>
}