package com.java.coroutinesample.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class DataModel {
    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("serviceName")
    @Expose
    private var serviceName: String? = null

    @SerializedName("imageUrl")
    @Expose
    private var imageUrl: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getServiceName(): String? {
        return serviceName
    }

    fun setServiceName(serviceName: String?) {
        this.serviceName = serviceName
    }

    fun getImageUrl(): String? {
        return imageUrl
    }

    fun setImageUrl(imageUrl: String?) {
        this.imageUrl = imageUrl
    }

}