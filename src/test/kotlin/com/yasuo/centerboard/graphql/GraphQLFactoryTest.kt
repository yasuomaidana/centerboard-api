package com.yasuo.centerboard.graphql

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpHeaders.ACCEPT
import io.micronaut.http.HttpRequest.POST
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
class GraphQLFactoryTest {
    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    private val query = """
    {
    accounts {
        id
        name
        positions{
        id
        ticker
        }
    }
    }    
    """.trimIndent()

    data class QueryRequest(val query: String)

    @Test
    fun queryForAccountsAndPositions() {

        val response = client.toBlocking()
            .exchange(
                POST("/graphql", QueryRequest(query)), Argument.of(Map::class.java))

        assertEquals(HttpStatus.OK, response.status())
        assertNotNull(response.body())

        val response2 = client.toBlocking().retrieve(POST("/graphql",QueryRequest(query)).header(ACCEPT,"application/json"))
        val accounts = Json.decodeFromString<List<AccountPayload>>(response2.subSequence(20,response2.length-2).toString())
        assertEquals(1,accounts[0].id)
        assertEquals(2,accounts[1].id)
        assertEquals("APPL",accounts[0].positions[0].ticker)
        assertEquals(3,accounts.size)
    }
}

