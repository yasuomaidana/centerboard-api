package com.yasuo.centerboard.controllers

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


@MicronautTest
class HelloControllerTest{

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient
    @Test
    fun controllerSaysHello(){
        val response = client.toBlocking().retrieve("hello")
        assertEquals("Hello world",response)
    }
}