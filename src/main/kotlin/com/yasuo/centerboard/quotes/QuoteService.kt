package com.yasuo.centerboard.quotes

import com.yasuo.centerboard.graphql.schema.Quote
import io.micronaut.cache.annotation.CachePut
import jakarta.inject.Singleton

@Singleton
class QuoteService {

    private val quotes = mapOf(
        "ISLA" to Quote("A",500.0,"IDK IDK",1400.0,200.0,1L),
        "BARCO" to Quote("B",100.0,"IDK WK",1500.0,5.0,1L)
    )
    @CachePut
    suspend fun getQuote(symbol: String):Quote{
        return quotes[symbol]!!
    }
}