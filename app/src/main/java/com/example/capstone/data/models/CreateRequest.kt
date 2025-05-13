package com.example.capstone.data.models

data class CreateRequest(
    val ownerID: Long,
    val customerID: Long,
    val serviceID: Long,
    val courierID: Long,
    val statusID: Long,
    val pickupDate: String?,
    val deliveryDate: String?,
    val sackQuantity: Int,
    val comment: String?,
    val modeID: Long
)
