package com.codingwithmitch.fragmentsLists

import java.util.*

class reservationModelForAdapter {
    var name: String = ""
    var price: Double=  0.0
    var date: Date = Date()

    constructor() {}

    constructor(name: String, date: Date, prix: Double) {
        this.name = name
        this.date = date
        this.price = prix
    }
}