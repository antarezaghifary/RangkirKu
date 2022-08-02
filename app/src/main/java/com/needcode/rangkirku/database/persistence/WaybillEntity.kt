package com.needcode.rangkirku.database.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableWaybill")
data class WaybillEntity(
    //jika ada id autogen di false
    @PrimaryKey(autoGenerate = false)
    val waybill: String,
    val courier: String? = "",
    val status: String? = ""
)