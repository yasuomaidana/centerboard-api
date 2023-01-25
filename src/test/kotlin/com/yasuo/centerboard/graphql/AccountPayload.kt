package com.yasuo.centerboard.graphql

import kotlinx.serialization.Serializable

@Serializable
data class  AccountPayload(
    val id: Int,
    val name: String,
    val description: String = "",
    val taxable: Boolean = true,
    val positions: List<PositionsResponse>
)

@Serializable
data class PositionsResponse(val id:String, val ticker:String)
