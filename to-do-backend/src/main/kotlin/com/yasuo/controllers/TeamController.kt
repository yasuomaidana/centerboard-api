package com.yasuo.controllers

import com.yasuo.configuration.TeamConfiguration
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("team")
class TeamController(val teamConfiguration: TeamConfiguration) {
    @Get
    fun getTeam():String{
        return teamConfiguration.name!!
    }
    @Get("/all")
    fun getAll():TeamConfiguration{
        return teamConfiguration
    }
}