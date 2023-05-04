package com.yasuo.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class EmailService {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(EmailService::class.java)
    }
    abstract fun send(user:String, message: String)
}