package com.java.coroutinesample.network

import com.java.coroutinesample.model.DataResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface MyApi {
//    @GET("api/v1/employees")
    @GET("dummeyJson.php")
//    suspend fun getDataValues(@Header("app-id") Auth: String): Response<DataResponse>
    suspend fun getDataValues(): Response<DataResponse>


    companion object{
        operator fun invoke() : MyApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("https://www.eveserver.in/amit/backup/")
//                .baseUrl("http://dummy.restapiexample.com/")
                .baseUrl("https://www.eveserver.in/amit/backup/")
                .build()
                .create(MyApi::class.java)
        }
    }
}