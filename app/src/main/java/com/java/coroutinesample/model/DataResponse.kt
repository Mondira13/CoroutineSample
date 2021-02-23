package com.java.coroutinesample.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class DataResponse {
    @SerializedName("result")
    @Expose
    private var result: String? = null

    @SerializedName("beautyAndSpa")
    @Expose
    private var beautyAndSpa: List<DataModel?>? = null

    fun getResult(): String? {
        return result
    }

    fun setResult(result: String?) {
        this.result = result
    }

    fun getBeautyAndSpa(): List<DataModel?>? {
        return beautyAndSpa
    }

    fun setBeautyAndSpa(beautyAndSpa: List<DataModel?>?) {
        this.beautyAndSpa = beautyAndSpa
    }
}