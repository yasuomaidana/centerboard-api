package com.yasuo.timed

import com.yasuo.services.EmailServiceImplementation
import io.micronaut.scheduling.ScheduledExecutorTaskScheduler
import io.micronaut.scheduling.TaskExecutors
import jakarta.inject.Named
import jakarta.inject.Singleton
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*

@Singleton
class DelayedEmail(private val emailService: EmailServiceImplementation, @param:Named(TaskExecutors.SCHEDULED) private val taskScheduler: ScheduledExecutorTaskScheduler) {
    @DelicateCoroutinesApi
    fun send(user: String, message: String) {
        LOG.info("Sending {} at {}", user, SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date()))
        GlobalScope.launch {
            delay(Duration.ofMinutes(1).toMillis())
            emailService.send(user, message)
        }
    }


    companion object {
        private val LOG = LoggerFactory.getLogger(DelayedEmail::class.java)
    }
}