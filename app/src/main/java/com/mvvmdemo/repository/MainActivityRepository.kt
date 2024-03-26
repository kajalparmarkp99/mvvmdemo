package com.mvvmdemo.repository

import ProductResponseItem
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mvvmdemo.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val products = MutableLiveData<List<ProductResponseItem>>()

    fun getProductApiCall(): MutableLiveData<List<ProductResponseItem>> {

        val call = RetrofitClient.apiInterface.getProducts()

        call.enqueue(object: Callback<List<ProductResponseItem>> {
            override fun onFailure(call: Call<List<ProductResponseItem>>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<ProductResponseItem>>,
                response: Response<List<ProductResponseItem>>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                products.value = data!!
            }
        })

        return products
    }
}