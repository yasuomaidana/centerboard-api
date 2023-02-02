package com.yasuo.centerboard.graphql.schema

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.yasuo.centerboard.repositories.AccountRepository
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import java.util.*

@GraphQLDescription("Represents a given position")
data class Position(
    val id:String,
    val ticker:String,
    val shares: String,
    val price: String,
    val date: Date = Date()
    )

@GraphQLDescription("Represents a given account")
data class Account (val id:String,
                    val name:String,
                    val description:String = "",
                    val taxable: Boolean = true
                    )
@GraphQLDescription("Represents a quote")
data class Quote(
    private val symbol: String,
    private val price:Double,
    private val companyName:String,
    private val high:Double,
    private val low: Double,
    private val lastUpdate:Long)

class AccountQuery(@Inject var accountRepository: AccountRepository) {

    @Suppress("unused")
    @GraphQLDescription("Use this query to get all the accounts")
    suspend fun accounts(): List<Account>{
        delay(1000)
        return accountRepository.getAccounts()
    }
}