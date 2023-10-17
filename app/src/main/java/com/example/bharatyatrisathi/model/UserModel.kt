package com.example.bharatyatrisathi.model

class UserModel {
    var name:String?= null
    var email:String?= null
    var password:String?= null
    var confirmPassword:String?= null

    constructor()
    constructor(name: String?, email: String?, password: String?, confirmPassword: String?) {
        this.name = name
        this.email = email
        this.password = password
        this.confirmPassword = confirmPassword
    }
}