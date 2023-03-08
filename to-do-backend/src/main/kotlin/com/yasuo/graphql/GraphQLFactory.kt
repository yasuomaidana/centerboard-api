package com.yasuo.graphql

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.toSchema
import com.yasuo.graphql.schemas.AuthorQuery
import graphql.GraphQL
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.core.io.ResourceResolver
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Factory
class GraphQLFactory {
    @Inject
    lateinit var authorQuery: AuthorQuery
    @Bean
    @Singleton
    fun graphQL(resourceResolver: ResourceResolver): GraphQL{
        val config = SchemaGeneratorConfig(supportedPackages = listOf("com.yasuo.graphql.schemas"))
        val queries = listOf(TopLevelObject(authorQuery))
        return GraphQL.newGraphQL(toSchema(config, queries, listOf())).build()
    }
}