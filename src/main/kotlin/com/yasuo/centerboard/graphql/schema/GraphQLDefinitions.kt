package com.yasuo.centerboard.graphql.schema

import com.yasuo.centerboard.quotes.QuoteService
import com.yasuo.centerboard.repositories.AccountRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.*

data class Position(
    val id:String,
    val ticker:String,
    val shares: String,
    val price: String,
    val date: String = Date().toString()
    )

data class Account (val id:String,
                    val name:String,
                    val description:String = "",
                    val taxable: Boolean = true,
                    val positions:List<Position>
                    )
data class Quote(
    val symbol: String,
    val price:Double,
    val companyName:String,
    val high:Double,
    val low: Double,
    val lastUpdate:Double)


@Singleton
@Suppress("unused")
class AccountQuery{
    @Inject
    lateinit var accountRepository: AccountRepository
    fun accounts():List<Account>{
        return accountRepository.getAccounts()
    }
}

@Singleton
@Suppress("unused")
class QuoteQuery{
    @Inject
    lateinit var quoteService: QuoteService
    fun quotes(): List<Quote>{
        return quoteService.getQuotes()
    }

    suspend fun quote(symbol: String):Quote{
        return quoteService.getQuoteBySymbol(symbol)
    }
}
