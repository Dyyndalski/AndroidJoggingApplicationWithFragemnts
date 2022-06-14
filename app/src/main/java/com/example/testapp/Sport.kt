package com.example.testapp;

class Sport {
    var id: Long? = null
    var name: String? = null
    var details: String? = null
    var recordTime: String? = null
    var recordDate: String? = null
    var lastTime: String? = null
    var lastDate: String? = null

    constructor() {}
    constructor(name: String?, details: String?) {
        this.name = name
        this.details = details
    }
}