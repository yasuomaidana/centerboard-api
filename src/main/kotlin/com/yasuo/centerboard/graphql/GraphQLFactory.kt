package com.yasuo.centerboard.graphql

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.toSchema
import com.yasuo.centerboard.graphql.schema.Account
import com.yasuo.centerboard.graphql.schema.AccountQuery
import com.yasuo.centerboard.graphql.schema.Position
import com.yasuo.centerboard.repositories.AccountRepository
import graphql.GraphQL
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.core.io.ResourceResolver
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Factory
class GraphQLFactory {

    @Inject
    lateinit var accountRepository: AccountRepository

    @Bean
    @Singleton
    fun graphQL(resourceResolver: ResourceResolver):GraphQL{
        val config =  SchemaGeneratorConfig(supportedPackages = listOf("com.yasuo.centerboard.graphql.schema"))
        val queries = listOf(TopLevelObject(AccountQuery(accountRepository)))
        return  GraphQL.newGraphQL(toSchema(config,queries, emptyList())).build()
    }
}

@Singleton
class PositionFetcher:DataFetcher<List<Position>>{
    private val positions = mapOf(
            "1" to listOf(Position("1","APPL","22","1.2")),
            "2" to listOf(Position("1","AMZ","10","3.2")),
            "3" to listOf(Position("1","ALF","10","4.2"))
        )
    override fun get(environment: DataFetchingEnvironment): List<Position> {

        val account =  environment.getSource<Account>()
        return positions.getOrDefault(account.id, emptyList())
    }

}

@Singleton
class AccountsFetcher:DataFetcher<List<Account>>{
    override fun get(environment: DataFetchingEnvironment): List<Account> {
        return listOf(
            Account("1","Savings","Hehe I don have savings"),
            Account("2","Expenses","I have a lot"),
            Account("3","Others","Mmmm mmm suspicious")
        )
    }

}