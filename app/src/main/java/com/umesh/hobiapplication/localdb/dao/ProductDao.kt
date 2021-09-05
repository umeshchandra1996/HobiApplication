package com.umesh.hobiapplication.localdb.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.umesh.hobiapplication.localdb.modeldata.Product

@Dao
interface  ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: Product): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductListAll(product: List<Product>): List<Long>

    @Query("select id from product where productName Like :name and productType Like:type")
    fun getProductIDbyNameAndType(name:String,type:String):Product

    @Query("select * from product where id Like :id")
    fun getProductDetailsByID(id:String): Product

    @Query("select * from product")
    fun getAllProductDetails(): List<Product>

    @Query("DELETE FROM Product WHERE id LIKE :id ")
    fun deleteProductByID(id:Long)
    @Query("SELECT * From Product where count>=1")
    fun getAllCartProductDetails(): List<Product>


//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertProductByUserList(product: List<ProductInCart>): List<Long>

    // Paged Product query with user search input, matching the sequence of characters of the string
  //  WHERE Productid like :query || '%' ORDER BY Productid DESC

    @Query("SELECT * FROM product ")
    fun getPagedProducts() : PagingSource<Int, Product>

}
