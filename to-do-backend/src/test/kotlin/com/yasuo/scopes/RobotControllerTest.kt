package com.yasuo.scopes

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

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

}