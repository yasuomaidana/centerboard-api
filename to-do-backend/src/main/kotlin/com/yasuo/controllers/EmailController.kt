package com.yasuo.controllers

import com.yasuo.timed.DelayedEmail
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import kotlinx.coroutines.DelicateCoroutinesApi

@Controller("/mail")
class EmailController(private val delayedEmail: DelayedEmail) {
    @DelicateCoroutinesApi
    @Post
    fun sendAfter30seconds(){
        delayedEmail.send("Request", "Sent from request")
    }
}