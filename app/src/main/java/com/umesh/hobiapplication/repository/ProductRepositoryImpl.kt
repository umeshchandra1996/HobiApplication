package com.umesh.hobiapplication.repository

import androidx.paging.PagingSource
import com.umesh.hobiapplication.localdb.dao.ProductDao
import com.umesh.hobiapplication.localdb.modeldata.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(private var productDao: ProductDao) :
    ProductRepository {
    override fun addProduct(product: Product): Long {
        return productDao.insertProduct(product)
    }

    override fun addProductList(products: List<Product>): List<Long> {
        return productDao.insertProductListAll(products)
    }

/*    override fun addProductListINCart(products: List<ProductInCart>): List<Long> {
        return  productDao.insertProductByUserList(products)
    }*/

    override fun getProductList(): List<Product> {
        return productDao.getAllProductDetails()
    }

    override fun getProductCartList(): List<Product> {
        return productDao.getAllCartProductDetails()
    }

    override fun getProductDetailsByID(id: String): Product {
        return productDao.getProductDetailsByID(id)
    }

    override fun getPagedProducts(): PagingSource<Int, Product> {
        return productDao.getPagedProducts()
    }


}