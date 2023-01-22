package com.yasuo.centerboard.graphql

import java.util.*

data class Position(
    val id:String,
    val ticker:String,
    val shares: String,
    val price: String,
    val date: Date = Date()
    )