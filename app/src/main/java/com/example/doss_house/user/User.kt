package com.example.doss_house.user

class User ()   {
    var id : String
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
        this.id = ""
    }
    constructor (id : String, dateOfBirth : String, email : String, name : String,
                 surname : String, phone : String, sex : Boolean) : this() {
        this.id = id
        this.email = email
        this.name = name
        this.surname = surname
        this.dateOfBirth = dateOfBirth
        this.phone = phone
        this.sex = sex
    }
    constructor(user: User) : this(){
        id = user.id
        dateOfBirth= user.dateOfBirth
        email = user.email
        name = user.name
        surname = user.surname
        sex = user.sex
        phone = user.phone
    }
}