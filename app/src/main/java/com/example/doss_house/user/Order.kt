package com.example.doss_house.user



data class Order(
    var userID : String,
    var id : String,
    var direction : String,
    var date : String,
    var time : String,
    var amount : String,
    var price : String
)
