package com.yasuo.clients

import com.yasuo.configuration.GithubConfiguration
import com.yasuo.dto.GithubRelease
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpHeaders.ACCEPT
import io.micronaut.http.HttpHeaders.USER_AGENT
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import jakarta.inject.Singleton
import reactor.core.publisher.Mono
import java.net.URI



@Singleton
class GithubLowLevelClient(@param:Client(id = "github") private val httpClient: HttpClient, configuration: GithubConfiguration) {
    private val uri: URI = UriBuilder.of("/repos")
        .path(configuration.organization)
        .path(configuration.repo)
        .path("releases")
        .build()

    fun fetchReleases(): Mono<List<GithubRelease>>{
        val req: HttpRequest<*> = HttpRequest.GET<Any>(uri)
            .header(USER_AGENT,"Micronaut HTTP Client")
            .header(ACCEPT, "application/vnd.github.v3+json, application/json")
        return Mono.from(httpClient.retrieve(req, Argument.listOf(GithubRelease::class.java)))
    }
}