package com.yasuo.scopes.singleton

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@MicronautTest
class RobotControllerTest(@Client("/singleton") val client: HttpClient){
    @Test
    fun onlyOneInstanceOfTheBeanExistsForSingletonBeans(){
        val res: HashSet<String>? = (client.toBlocking().retrieve(HttpRequest.GET<Any>(""),HashSet::class.java) as? HashSet<String>)

        assertNotNull(res)

        assertEquals(1, res!!.size,"It has more elements than expected")

    }

}