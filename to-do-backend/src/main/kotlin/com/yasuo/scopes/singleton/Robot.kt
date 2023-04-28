package com.yasuo.scopes.singleton

import jakarta.inject.Singleton
import java.util.*

@Singleton
data class Robot(val serialNumber:String = UUID.randomUUID().toString())