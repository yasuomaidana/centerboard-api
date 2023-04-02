package com.yasuo.configuration

import io.micronaut.context.annotation.ConfigurationBuilder
import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("team")
class TeamConfiguration {
    var name: String? = null
    var color: String? = null
    var playerNames: List<String>? = null
    @ConfigurationBuilder(prefixes = ["with"], configurationPrefix = "team-admin")
    var teamAdmin = TeamAdmin.Builder()
}

class TeamAdmin private constructor(
        val manager: String?,
        val coach: String?,
        val president: String?) {
    data class Builder(
            var manager: String? = null,
            var coach: String? = null,
            var president: String? = null) {
        fun withManager(manager: String) = apply { this.manager = manager }
        fun withCoach(coach: String) = apply { this.coach = coach }
        fun withPresident(president: String) = apply { this.president = president }
        fun build() = TeamAdmin(manager, coach, president)
    }
}