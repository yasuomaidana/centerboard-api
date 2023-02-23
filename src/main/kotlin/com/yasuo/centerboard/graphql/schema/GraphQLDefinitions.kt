package com.yasuo.centerboard.graphql.schema

import com.yasuo.centerboard.quotes.QuoteService
import com.yasuo.centerboard.repositories.AccountRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
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
    val logger = LoggerFactory.getLogger(QuoteQuery::class.java)
    fun quotes(): List<Quote>{
        logger.debug("Asking service for quotes QUERY")
        return quoteService.getQuotes()
    }

    suspend fun quote(symbol: String):Quote{
        logger.debug("Asking service for {} symbol",symbol)
        return quoteService.getQuoteBySymbol(symbol)
    }
}
