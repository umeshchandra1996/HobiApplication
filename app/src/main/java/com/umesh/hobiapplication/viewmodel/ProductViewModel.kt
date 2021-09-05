package com.umesh.hobiapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.umesh.hobiapplication.datasource.ProductPagedDataSource
import com.umesh.hobiapplication.localdb.modeldata.Product
import com.umesh.hobiapplication.resource.Resource
import com.umesh.hobiapplication.usecase.ProductUseCase


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
 class ProductViewModel @Inject constructor(private var productUseCase: ProductUseCase, private var productPagedDataSource: ProductPagedDataSource) : ViewModel() {

    private val _insertProductsDataStatus = MutableLiveData<Resource<Long>>()

    val insertProductsDataStatus: LiveData<Resource<Long>> = _insertProductsDataStatus

    private val _insertProductsDataStatusList = MutableLiveData<Resource <List<Long>>>()

    val insertProductsDataStatusList: LiveData<Resource<Long>> = _insertProductsDataStatus

    private val _insertProductsInCartDataStatusList = MutableLiveData<Resource <List<Long>>>()

    val insertProductsInCartDataStatusList: LiveData<Resource<Long>> = _insertProductsDataStatus


    private val _getProductsDataStatusList = MutableLiveData<Resource <List<Product>>>()

    val getProductsDataStatusList: LiveData<Resource<List<Product>>> = _getProductsDataStatusList


    /**
     * Channel for Product search queries sent from fragment
     */
    private val _searchProductResults = Channel<PagingData<Product>>(Channel.CONFLATED)
    val searchProductResults = _searchProductResults.receiveAsFlow()

    val getPagedProductListData=productPagedDataSource.execute().cachedIn(viewModelScope)




    fun insertUserData(product: Product) {
        viewModelScope.launch {
            _insertProductsDataStatus.postValue(Resource.loading(null))
            try {
                val data = productUseCase.addProductByUser(product = product)
                _insertProductsDataStatus.postValue(Resource.success(data, 0))
            } catch (exception: Exception) {
                _insertProductsDataStatus.postValue(Resource.error(null, exception.message!!))
            }
        }
    }

    fun insertProductsDataList(products: List<Product>) {
        viewModelScope.launch {
            _insertProductsDataStatusList.postValue(Resource.loading(null))
            try {
                val data = productUseCase.addProductListOnAppOpen(products)
                _insertProductsDataStatusList.postValue(Resource.success(data, 0))
            } catch (exception: Exception) {
                _insertProductsDataStatusList.postValue(Resource.error(null, exception.message!!))
            }
        }
    }
//
//    fun insertProductsInCartDataList(products: List<ProductInCart>) {
//        viewModelScope.launch {
//            _insertProductsInCartDataStatusList.postValue(Resource.loading(null))
//            try {
//                val data = productUseCase.addProductListInCart(products)
//                _insertProductsInCartDataStatusList.postValue(Resource.success(data, 0))
//            } catch (exception: Exception) {
//                _insertProductsInCartDataStatusList.postValue(Resource.error(null, exception.message!!))
//            }
//        }
//    }

    fun getProductDataFormRoomDB(){
        viewModelScope.launch {
            _getProductsDataStatusList.postValue(Resource.loading(null))

            try {
                val data=productUseCase.getProductList()
                _getProductsDataStatusList.postValue(Resource.success(data =data,0 ))
            }
            catch (e:Exception){
                _getProductsDataStatusList.postValue(Resource.error(null,e.localizedMessage!!))
            }
        }

    }


    fun getCartProductDataFormRoomDB(){
        viewModelScope.launch {
            _getProductsDataStatusList.postValue(Resource.loading(null))

            try {
                val data=productUseCase.getProductCartList()
                _getProductsDataStatusList.postValue(Resource.success(data =data,0 ))
            }
            catch (e:Exception){
                _getProductsDataStatusList.postValue(Resource.error(null,e.localizedMessage!!))
            }
        }

    }

    fun getPagedProductList(){}

}