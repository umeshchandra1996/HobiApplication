package com.umesh.hobiapplication.localdb.modeldata

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.umesh.hobiapplication.localdb.DbConstant
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = DbConstant.PRODUCT_TABLE,indices = [Index(value = ["id","productName"], unique = true)])
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "productName")
    var productName: String? = "",
    var productPrice: Int? = 0,
    var productType: String? = "",
    var image: String? = "",
    val companyName: String? = "",
    var count: Int? = 0,
    var addBuyUser:String?="no"
): Parcelable