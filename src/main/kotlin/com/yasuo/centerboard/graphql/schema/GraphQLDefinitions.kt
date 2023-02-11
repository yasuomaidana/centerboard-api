package com.yasuo.centerboard.graphql.schema

import java.util.*

data class Position(
    val id:String,
    val ticker:String,
    val shares: String,
    val price: String,
    val date: Date = Date()
    )

data class Account (val id:String,
                    val name:String,
                    val description:String = "",
                    val taxable: Boolean = true
                    )
data class Quote(
    private val symbol: String,
    private val price:Double,
    private val companyName:String,
    private val high:Double,
    private val low: Double,
    private val lastUpdate:Long)