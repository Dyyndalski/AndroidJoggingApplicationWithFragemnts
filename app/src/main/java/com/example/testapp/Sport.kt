package com.example.testapp;

class Sport {
    var id: Long? = null
    var name: String? = null
    var details: String? = null

    constructor() {}

    constructor(name: String?, details: String?) {
        this.name = name
        this.details = details
    }

}