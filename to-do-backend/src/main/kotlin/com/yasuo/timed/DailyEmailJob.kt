package com.yasuo.timed

import com.yasuo.services.EmailService
import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Singleton

@Singleton
class DailyEmailJob(private val emailService: EmailService) {
    @Scheduled(cron = "0 06 19 1/1 * ?")
    fun execute() {
        emailService.send("john.doe@micronaut.example", "Test Message")
    }
}