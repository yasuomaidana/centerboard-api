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
        val res: HashSet<String>? = (client.toBlocking().retrieve(HttpRequest.GET<Any>("/singleton"),HashSet::class.java) as? HashSet<String>)

        assertNotNull(res)

        assertEquals(1, res!!.size,"It has more elements than expected")

    }

    @Test
    fun differentInstancesOfTheBeanExistsForPrototype(){
        val res: HashSet<String>? = (client.toBlocking().retrieve(HttpRequest.GET<Any>("/prototype"),HashSet::class.java) as? HashSet<String>)

        assertNotNull(res)

        assertEquals(2, res!!.size,"It has less elements than expected")

    }

    @Test
    fun differentInstancesOfTheBeanExistsForRequest(){
        val res: HashSet<String>? = (client.toBlocking().retrieve(HttpRequest.GET<Any>("/request").header("UUID",UUID.randomUUID().toString()),HashSet::class.java) as? HashSet<String>)
        val res2: HashSet<String>? = (client.toBlocking().retrieve(HttpRequest.GET<Any>("/request").header("UUID",UUID.randomUUID().toString()),HashSet::class.java) as? HashSet<String>)

        assertNotNull(res)
        assertEquals(1, res!!.size,"It has more elements than expected")
        assertNotEquals(res,res2,"The answer is the same")
    }

}