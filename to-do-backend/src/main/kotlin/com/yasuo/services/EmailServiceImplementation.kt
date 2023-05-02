package com.yasuo.services

import jakarta.inject.Singleton
import java.text.SimpleDateFormat
import java.util.*

@Singleton
class EmailServiceImplementation: EmailService() {
    override fun send(user: String, message: String) {
        LOG?.info("Sending email to {} : {} at {}", user, message, SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date()))
    }
}