package com.example.appfirebase

data class User(
    var firstName: String? = null,
    var lastName: String? = null,
    var age: String? = null
) {
    override fun toString(): String {
        return "$firstName\t$lastName\t$age"
    }
}