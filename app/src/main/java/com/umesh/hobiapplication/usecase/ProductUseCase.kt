package com.umesh.hobiapplication.usecase

import androidx.paging.PagingSource
import com.umesh.hobiapplication.localdb.modeldata.Product


interface ProductUseCase {
     fun addProductByUser(product: Product): Long
     fun addProductListOnAppOpen(products: List<Product>): List<Long>

    //    fun addProductListInCart(products: List<ProductInCart>): List<Long>
     fun getProductList(): List<Product>
     fun getProductCartList(): List<Product>
     fun getProductDetailsByID(id: String): Product
     fun getPagedProducts(): PagingSource<Int, Product>

}
