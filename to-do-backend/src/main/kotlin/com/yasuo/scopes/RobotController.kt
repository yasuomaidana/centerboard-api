package com.yasuo.scopes

import com.yasuo.scopes.singleton.RobotFather
import com.yasuo.scopes.singleton.RobotMother
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.util.*

@Controller("/")
@Suppress("unused")
class RobotController(private val father: RobotFather, private val mother: RobotMother,
                      private  val prototypeMother: com.yasuo.scopes.prototype.RobotMother,
                      private val prototypeFather: com.yasuo.scopes.prototype.RobotFather,
                      private val requestFather: com.yasuo.scopes.request_scope.RobotFather,
                      private val requestMother: com.yasuo.scopes.request_scope.RobotMother
                        ) {



    @Get("/singleton")
    fun children():List<String>{
        return listOf(father.child().serialNumber, mother.child().serialNumber)
    }

    @Get("/prototype")
    fun prototypeRobot():List<String>{
        return listOf(prototypeFather.child().serialNumber, prototypeMother.child().serialNumber)
    }

    @Get("/request")
    fun requestRobot():List<String>{

        return listOf(requestFather.child().serialNumber, requestMother.child().serialNumber)
    }

}