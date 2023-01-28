package com.yasuo.centerboard.graphql.schema

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.yasuo.centerboard.repositories.AccountRepository
import jakarta.inject.Inject
import kotlinx.coroutines.delay
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

class AccountQuery(@Inject var accountRepository: AccountRepository) {

    @Suppress("unused")
    @GraphQLDescription("Use this query to get all the accounts")
    suspend fun accounts(): List<Account>{
        delay(1000)
        return accountRepository.getAccounts()
    }
}