package com.yasuo.timed

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import jakarta.inject.Singleton
import kotlinx.coroutines.DelicateCoroutinesApi

@Singleton
class StartUpMail(private val delayedEmail: DelayedEmail):ApplicationEventListener<ServerStartupEvent> {

    @DelicateCoroutinesApi
    override fun onApplicationEvent(event: ServerStartupEvent?) {
        delayedEmail.send("Server Startup","This is sent after startup")
    }
}