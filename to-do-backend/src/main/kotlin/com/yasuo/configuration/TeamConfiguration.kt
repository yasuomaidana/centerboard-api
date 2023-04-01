package com.yasuo.configuration

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("team")
class TeamConfiguration {
    var name: String? = null
    var color: String? = null
    var playerNames: List<String>? = null
}