package com.umesh.hobiapplication.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.umesh.hobiapplication.localdb.modeldata.Product
import com.umesh.hobiapplication.usecase.ProductUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductPagedDataSource @Inject constructor(private  val  productUseCase: ProductUseCase) {
    fun execute():Flow<PagingData<Product>>{
        return  Pager(
            config= PagingConfig(
                pageSize=10,
                //prefetchDistance= 5,
                enablePlaceholders=false,
               // initialLoadSize=30,
                //maxSize= 60,
//                jumpThreshold=1
            ),
//            initialKey=null,
            pagingSourceFactory= {
            productUseCase.getPagedProducts()
        }
        ).flow
    }
}