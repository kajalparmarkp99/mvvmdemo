package com.mvvmdemo.viewmodel

import ProductResponseItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvmdemo.repository.MainActivityRepository

class MainActivityViewModel : ViewModel() {

    private var productsLiveData: MutableLiveData<List<ProductResponseItem>> = MutableLiveData()

    fun getProducts(): LiveData<List<ProductResponseItem>> {
        if (productsLiveData.value == null) {
            fetchProducts()
        }
        return productsLiveData
    }

    private fun fetchProducts() {
        MainActivityRepository.getProductApiCall().observeForever { productsResponse ->
            productsLiveData.value = productsResponse
        }
    }

}