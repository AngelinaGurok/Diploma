package com.example.doss_house

data class Ticket(
    val time : String,
    val price : Double,
    val transportId : String, //номер поезда или номер маршрутки
    val departurePint : String,
    val arrivalPoint : String
)
