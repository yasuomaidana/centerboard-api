package com.yasuo.graphql.schemas

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@MicronautTest
class ToDoMutationTest(@Client("/") val client: HttpClient){
    @Test
    fun testItWorks() {
        val query = "{ \"query\": \"{ getToDos {title, author{ username } }}\" }"

        val request: HttpRequest<String> = HttpRequest.POST("/graphql", query)
        val rsp = client.toBlocking().exchange(request, Argument.of(Map::class.java))

        Assertions.assertEquals(HttpStatus.OK, rsp.status())
        Assertions.assertNotNull(rsp.body())

        val toDos = rsp.getBody(Map::class.java).get()["data"] as Map<*, *>
    }
}