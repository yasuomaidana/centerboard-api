package com.yasuo.scopes.prototype

import io.micronaut.context.annotation.Prototype
import java.util.*

@Prototype
data class Robot(val serialNumber:String = UUID.randomUUID().toString())
