package com.yasuo.centerboard.quotes

import com.yasuo.centerboard.graphql.schema.Quote
import io.micronaut.cache.annotation.CacheConfig
import io.micronaut.cache.annotation.CachePut
import jakarta.inject.Singleton
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory

@Singleton
@CacheConfig("quotes")
class QuoteService {

    private val logger = LoggerFactory.getLogger(QuoteService::class.java)
    val quotes = mapOf(
        "ISLA" to Quote("A",500.0,"IDK IDK",1400.0,200.0,1.0),
        "BARCO" to Quote("B",100.0,"IDK WK",1500.0,5.0,1.0)
    )
    suspend fun getQuoteBySymbol(symbol: String):Quote{

        logger.debug("Asking for {}",symbol)
        delay(1000)
        return quotes[symbol]!!
    }

    @CachePut
    fun getQuotes():List<Quote>{
        logger.debug("Retrieving quotes")
        return quotes.map { (_,value) -> return listOf(value) }
    }
}