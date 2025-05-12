package com.example.capstone.data.models


data class Request(
    val requestID: Long,
    val ownerID: Long,
    val customerID: Long,
    val customerName: String,
    val serviceID: Long,
    val serviceName: String,
    val selectedServices: List<String>?,
    val courierID: Long,
    val statusID: Long,
    val pickupDate: String?,
    val paymentMethod: String,
    val deliveryDate: String?,
    val sackQuantity: Int,
    val comment: String,
    val dateCreated: String,
    val dateUpdated: String
)
