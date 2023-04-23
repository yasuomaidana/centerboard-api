package com.yasuo.scopes.singleton

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.util.*

@Controller("/singleton")
@Suppress("unused")
class RobotController(private val father: RobotFather, private val mother: RobotMother) {
    @Get
    fun children():List<String>{
        return listOf(father.child().serialNumber, mother.child().serialNumber)
    }
}