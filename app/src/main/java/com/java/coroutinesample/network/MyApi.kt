package com.java.coroutinesample.network

import com.java.coroutinesample.model.DataResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MyApi {

    @GET("dummeyJson.php")
    suspend fun getDataValues(): Response<DataResponse>

    companion object{
        operator fun invoke() : MyApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.eveserver.in/amit/backup/")
                .build()
                .create(MyApi::class.java)
        }
    }
}