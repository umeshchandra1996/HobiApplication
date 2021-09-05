package com.umesh.hobiapplication.localdb.modeldata

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.umesh.hobiapplication.localdb.DbConstant
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = DbConstant.PRODUCT_CART)
data class ProductInCart(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var productName: String? = "",
    var productPrice: Int? = 0,
    var productType: String? = "",
    var image: String? = "",
    var companyName: String? = "",
    var count: Int? = 0,
):Parcelable
