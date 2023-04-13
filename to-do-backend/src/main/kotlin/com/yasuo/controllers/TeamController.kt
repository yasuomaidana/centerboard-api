package com.yasuo.controllers

import com.yasuo.configuration.StadiumConfiguration
import com.yasuo.configuration.TeamAdmin
import com.yasuo.configuration.TeamConfiguration
import io.micronaut.context.ApplicationContext
import io.micronaut.context.exceptions.NoSuchBeanException
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.inject.qualifiers.Qualifiers
import jakarta.inject.Inject
import jakarta.inject.Named

@Controller("team")
class TeamController(val teamConfiguration: TeamConfiguration, @Named("pnc") val stadiumConfiguration: StadiumConfiguration) {
    @Inject
    lateinit var applicationContext: ApplicationContext
    @Get
    fun getTeam():String{
        return teamConfiguration.name!!
    }
    @Get("/all")
    fun getAll():TeamConfiguration{
        return teamConfiguration
    }

    @Get("/admin")
    fun getAdmin():TeamAdmin{
        return teamConfiguration.teamAdmin.build()
    }
    @Get("/stadium")
    fun defaultStadium(): StadiumConfiguration{
        return stadiumConfiguration
    }

    @Get("/stadium/{name}")
    fun stadium(@QueryValue name:String): StadiumConfiguration? {
        val prop: StadiumConfiguration
        try {
             prop = applicationContext.getBean(StadiumConfiguration::class.java,Qualifiers.byName(name))
        } catch (nsb:NoSuchBeanException){
            return null
        }

        return  prop
    }
}