package com.example.capstone.data.models

import com.google.gson.annotations.SerializedName


data class Request(
    @SerializedName("requestID") val requestID: Long,
    @SerializedName("ownerID") val ownerID: Long,
    @SerializedName("customerID") val customerID: Long,
    @SerializedName("customerName") val customerName: String,
    @SerializedName("serviceID") val serviceID: Long,
    @SerializedName("serviceName") val serviceName: String,
    @SerializedName("courierID") val courierID: Long,
    @SerializedName("statusID") val statusID: Long,
    @SerializedName("pickupDate") val pickupDate: String?,
    val paymentMethod: String,
    @SerializedName("deliveryDate") val deliveryDate: String?,
    @SerializedName("sackQuantity") val sackQuantity: Int,
    @SerializedName("comment") val comment: String,
    val dateCreated: String,
    val dateUpdated: String,
    @SerializedName ("schedule") val schedule: String?,
    @SerializedName ("submittedAt") val submittedAt: String?
)
