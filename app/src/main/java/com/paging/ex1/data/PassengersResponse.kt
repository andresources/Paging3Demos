package com.paging.ex1.data

data class PassengersResponse(
    val data: List<Passenger>,
    val totalPages: Int,
    val totalPassengers: Int
)