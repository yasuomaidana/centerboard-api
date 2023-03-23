package com.yasuo.graphql.schemas

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
class ToDoMutationTest(@Client("/") val client: HttpClient){
    @Test
    fun testItWorks() {


        val todos = allTodos
        val username = (todos[0]["author"] as Map<String,String>)["username"]
        assert(todos.isNotEmpty())
        assert(username!!.any())
    }

    private fun fetch(query: String): HttpResponse<Map<String, Any>> {
        val request: HttpRequest<String> = HttpRequest.POST("/graphql", query)
        val response = client.toBlocking().exchange(
            request, Argument.mapOf(Argument.STRING, Argument.OBJECT_ARGUMENT)
        )
        assertEquals(HttpStatus.OK, response.status())
        Assertions.assertNotNull(response.body())
        return response
    }

    private val allTodos:List<Map<String,Any>>
        get(){
            val query = "{ \"query\": \"{ toDos {title, author{ username } }}\" }"
            val response = fetch(query)
            return (response.body.get()["data"] as Map<*,*>)["toDos"] as List<Map<String, Any>>
        }
}