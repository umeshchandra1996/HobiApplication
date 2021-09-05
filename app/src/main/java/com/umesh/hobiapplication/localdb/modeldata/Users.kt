package com.umesh.hobiapplication.localdb.modeldata

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.umesh.hobiapplication.localdb.DbConstant

@Entity(tableName = DbConstant.USER_TABLE)
data class Users(
    @PrimaryKey (autoGenerate = true)
    val id:Long=0,
    var mobNum: String?="",
    var password: String?="",
    var name:String?="",
    var profession:String?="",
    var email:String?="",
    )
