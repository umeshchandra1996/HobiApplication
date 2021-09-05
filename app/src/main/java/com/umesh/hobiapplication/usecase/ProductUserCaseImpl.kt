package com.umesh.hobiapplication.usecase

import androidx.paging.PagingSource
import com.umesh.hobiapplication.localdb.modeldata.Product
import com.umesh.hobiapplication.repository.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductUserCaseImpl @Inject constructor(private val productRepository: ProductRepository):ProductUseCase {
    override fun addProductByUser(product: Product): Long {
        return productRepository.addProduct(product)
    }

    override fun addProductListOnAppOpen(products: List<Product>): List<Long> {
       return productRepository.addProductList(products)
    }

   /* override fun addProductListInCart(products: List<ProductInCart>): List<Long> {
        return productRepository.addProductListINCart(products)
    }
*/
    override fun getProductList(): List<Product> {
        return productRepository.getProductList()
    }

    override fun getProductCartList(): List<Product> {
        return productRepository.getProductCartList()
    }

    override fun getProductDetailsByID(id:String): Product {
        return productRepository.getProductDetailsByID(id)
    }

    override fun getPagedProducts(): PagingSource<Int, Product> {
       return productRepository.getPagedProducts()
    }
}