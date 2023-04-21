package com.yasuo.controllers

import com.yasuo.clients.GithubApiClient
import com.yasuo.clients.GithubLowLevelClient
import com.yasuo.dto.GithubRelease
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

@Controller("/github")
class GithubController (
    private val githubLowLevelClient: GithubLowLevelClient,
    private val githubApiClient: GithubApiClient
    ) {

    @Get("/releases-low-level")
    fun releasesWithLowLevelClient(): Mono<List<GithubRelease>>{
        return githubLowLevelClient.fetchReleases()
    }

    @Get(uri = "/releases", produces = [MediaType.APPLICATION_JSON_STREAM])
    fun fetchReleases():Publisher<GithubRelease?>?{
        return githubApiClient.fetchReleases()
    }
}