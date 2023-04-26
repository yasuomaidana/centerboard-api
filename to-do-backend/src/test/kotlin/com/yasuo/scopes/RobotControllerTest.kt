package com.yasuo.scopes

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

@MicronautTest
class RobotControllerTest(@Client("/") val client: HttpClient){
    @Test
    fun onlyOneInstanceOfTheBeanExistsForSingletonBeans(){
        val res: HashSet<String> = execute_get("/singleton").toHashSet()

        assertNotNull(res)

        assertEquals(1, res.size,"It has more elements than expected")

    }

    @Test
    fun differentInstancesOfTheBeanExistsForPrototype(){
        val res: HashSet<String> = execute_get("/prototype").toHashSet()
        assertNotNull(res)
        assertEquals(2, res.size,"It has less elements than expected")

    }

    @Test
    fun differentInstancesOfTheBeanExistsForRequest(){
        val res: HashSet<String> = client.toBlocking().retrieve(HttpRequest.GET<Any>("/request").header("UUID",UUID.randomUUID().toString()),List::class.java).map{r -> r.toString()}.toHashSet()

        assertNotNull(res)
        assertEquals(1, res.size,"It has more elements than expected")

        client.toBlocking().retrieve(HttpRequest.GET<Any>("/request").header("UUID",UUID.randomUUID().toString()),List::class.java).forEach{r->res.add(r.toString())}
        assertEquals(2, res.size,"Less elements than expected")
    }

    @Test
    fun refreshRobot(){
        val res: HashSet<String> = execute_get("/refreshable").toHashSet()
        assertEquals(1, res.size,"It has more elements than expected")
        execute_get("/refreshable").forEach { r->res.add(r)}

        assertEquals(1, res.size,"It has more elements than expected")

        client.toBlocking().exchange(HttpRequest.POST("/refresh",Any::class.java),Any::class.java)
        execute_get("/refreshable").forEach { r->res.add(r)}
        assertEquals(2, res.size,"It has more elements than expected")
        execute_get("/refreshable").forEach { r->res.add(r)}
        assertEquals(2, res.size,"It has more elements than expected")
    }

    private fun execute_get(uri:String) = client.toBlocking().retrieve(HttpRequest.GET<Any>(uri),List::class.java).map { it.toString() }


}