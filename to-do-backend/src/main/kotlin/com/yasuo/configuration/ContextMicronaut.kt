package com.yasuo.configuration

import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Context
import java.util.*
import javax.validation.constraints.Pattern

@Context
@ConfigurationProperties("context_micronaut")
@Suppress("unused")
class ContextMicronaut {
    @Pattern(regexp = "groovy|java|kotlin")
    var language : String ? = null
    var id : String = UUID.randomUUID().toString()
}