package com.yasuo.centerboard.graphql

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.toSchema
import com.yasuo.centerboard.graphql.schema.AccountQuery
import com.yasuo.centerboard.graphql.schema.QuoteQuery
import graphql.GraphQL
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.core.io.ResourceResolver
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Factory
class GraphQLFactory {

    @Inject
    lateinit var accountQuery: AccountQuery
    @Inject
    lateinit var quoteQuery: QuoteQuery
    @Bean
    @Singleton
    fun graphQL(resourceResolver: ResourceResolver):GraphQL{

        val config = SchemaGeneratorConfig(supportedPackages = listOf("com.yasuo.centerboard.graphql.schema"))

        val queries = listOf(TopLevelObject(accountQuery), TopLevelObject(quoteQuery))

        return  GraphQL.newGraphQL(toSchema(config,queries, listOf())).build()
    }

}