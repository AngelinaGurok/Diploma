package com.example.doss_house.user

class User ()   {
    var email : String
    var name : String
    var surname : String
    var dateOfBirth : String
    var phone : String
    var sex : Boolean
    var status : Boolean
    init {
        this.dateOfBirth = ""
        this.email = ""
        this.name = ""
        this.surname = ""
        this.sex = false
        this.phone = ""
        this.status = true
    }
    constructor (email : String, name : String, surname : String,
                dateOfBirth : String, phone : String, sex : Boolean) : this() {
        this.dateOfBirth = dateOfBirth
        this.email = email
        this.name = name
        this.surname = surname
        this.sex = sex
        this.phone = phone
    }
    constructor(user: User) : this(){
        dateOfBirth= user.dateOfBirth
        email = user.email
        name = user.name
        surname = user.surname
        sex = user.sex
        phone = user.phone
    }
}