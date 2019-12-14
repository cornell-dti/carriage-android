package com.example.rider

data class UpcomingRide(
    val month: String,
    val day: String,
    val time: String,
    val from: String,
    val to: String,
    val driver: String,
    val phone: String
)