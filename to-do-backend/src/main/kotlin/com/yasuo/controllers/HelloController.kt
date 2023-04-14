package com.yasuo.controllers

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import java.io.File

@Controller("/hello")
class HelloController {

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    fun index() = "Holi"

    @Get("/image", produces = [MediaType.IMAGE_JPEG])
    fun getImage(): File {
        val resource = javaClass.getResource("/media/image/test.png")
        return File(resource!!.file)
    }

}