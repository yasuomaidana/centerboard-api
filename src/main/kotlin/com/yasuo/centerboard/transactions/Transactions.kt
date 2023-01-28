package com.yasuo.centerboard.transactions

import com.yasuo.centerboard.graphql.schema.Account
import java.math.BigDecimal

data class Transactions(val id: String,
                        val title: String,
                        val description: String,
                        val value: BigDecimal,
                        val account: Account
)