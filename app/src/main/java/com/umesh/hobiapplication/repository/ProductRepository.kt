package com.umesh.hobiapplication.repository

import androidx.paging.PagingSource
import com.umesh.hobiapplication.localdb.modeldata.Product

interface ProductRepository {
    fun addProduct(product: Product): Long
    fun addProductList(products: List<Product>): List<Long>
//    fun addProductListINCart(products: List<ProductInCart>):List<Long>
    fun getProductList(): List<Product>
    fun getProductCartList(): List<Product>
    fun getProductDetailsByID(id:String): Product
    fun getPagedProducts ():PagingSource<Int, Product>
}