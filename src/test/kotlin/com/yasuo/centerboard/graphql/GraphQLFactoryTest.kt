package com.yasuo.centerboard.graphql

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpRequest.POST
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

@MicronautTest
class GraphQLFactoryTest {
    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    val query = "{ \"query\": \"{ accounts{ name, description, positions { ticker } } }\" }"
    val copied_query = """
        accounts {
    taxable
    positions{
      ticker
      price
      shares
    }
  }
    """.trimIndent()
    data class QueryRequest(val query:String)
    @Test
    fun queryForAccountsAndPositions() {

        val request: HttpRequest<String> = HttpRequest.POST("/graphql", query)

        val rsp = client.toBlocking().exchange(request, Argument.of(Map::class.java))
        val accounts = rsp.getBody(Map::class.java).get()["data"] as Map<*, *>
        assertEquals(HttpStatus.OK, rsp.status())
        assertNotNull(rsp.body())

        val response = client.toBlocking().exchange<QueryRequest,String>(POST("/graphql",QueryRequest(copied_query)))
    }
}