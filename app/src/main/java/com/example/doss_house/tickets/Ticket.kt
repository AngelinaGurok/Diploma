package com.example.doss_house.tickets

data class Ticket(
    var arrivalPoint : String,
    var arrivalTime : String,
    var date : String,
    var depPoint : String,
    var depTime : String,
    var id : String,
    var price : Double,
    var tickets : Int
)
