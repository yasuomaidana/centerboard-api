package com.yasuo.controllers

import com.yasuo.configuration.StadiumConfiguration
import com.yasuo.configuration.TeamAdmin
import com.yasuo.configuration.TeamConfiguration
import io.micronaut.http.HttpRequest

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.*
import java.util.function.Consumer


@MicronautTest
class TeamControllerTest(@Client("/team") val client: HttpClient) {

    @Test
    fun getTeam() {
        val team = client.toBlocking()
                .retrieve(HttpRequest.GET<String>("/"))
        assertEquals("Steelers", team)
    }

    @Test
    fun getAll() {
        val teamConfiguration = client.toBlocking()
                .retrieve(HttpRequest.GET<Any>("/all"), TeamConfiguration::class.java)
        assertEquals("Steelers", teamConfiguration.name)
        assertEquals("Black", teamConfiguration.color)
        val expectedPlayers = listOf("Mason Rudolph", "James Connor")
        assertEquals(expectedPlayers.size, teamConfiguration.playerNames!!.size)
        expectedPlayers.forEach(Consumer { name: String? -> assertTrue(teamConfiguration.playerNames!!.contains(name!!)) })
    }

    @Test
    fun defaultStadium() {
        val stadium = client.toBlocking().retrieve(HttpRequest.GET<Any>("/stadium"),StadiumConfiguration::class.java)
        assertEquals("Pittsburgh",stadium.city)
    }

    @Test
    fun getAdmin() {
        val teamAdmin = client.toBlocking()
                .retrieve(HttpRequest.GET<Any>("/admin"), TeamAdmin::class.java)
        assertEquals("Mike Tomlin",teamAdmin.coach)
        assertEquals("Nirav Assar",teamAdmin.manager)
        assertEquals("Dan Rooney",teamAdmin.president)
    }
}