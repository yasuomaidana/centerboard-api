package com.yasuo.configuration

import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Requires

@ConfigurationProperties(GithubConfiguration.PREFIX)
@Requires(GithubConfiguration.PREFIX)
class GithubConfiguration {

    var organization: String? = null
    var repo: String? = null
    var username: String? = null
    var token: String? = null

    companion object {
        const val PREFIX = "github"
        const val GITHUB_API_URL = "https://api.github.com"
    }
}