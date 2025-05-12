package com.example.capstone.data.models


data class Request(
    val requestID: Long,
    val customerID: Long,
    val serviceID: Long,
    val courierID: Long,
    val statusID: Long,
    val pickupDate: String?,
    val deliveryDate: String?,
    val sackQuantity: Int,
    val comment: String,
    val dateCreated: String,
    val dateUpdated: String
)
