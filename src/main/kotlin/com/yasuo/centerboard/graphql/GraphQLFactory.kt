package com.yasuo.centerboard.graphql

import com.yasuo.centerboard.accounts.Account
import graphql.GraphQL
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeDefinitionRegistry
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.core.io.ResourceResolver
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.io.BufferedReader
import java.io.InputStreamReader

@Factory
class GraphQLFactory {

    @Inject
    lateinit var positionFetcher: PositionFetcher
    @Inject
    lateinit var accountsFetcher: AccountsFetcher

    @Bean
    @Singleton
    fun graphQL(resourceResolver: ResourceResolver):GraphQL{
        val parser = SchemaParser()
        val generator = SchemaGenerator()

         val typeRegistry = TypeDefinitionRegistry()
        typeRegistry.merge(parser.parse(
            BufferedReader(InputStreamReader(
                resourceResolver.getResourceAsStream("classpath:schema.graphqls").get()))))

        val runtimeWiring = RuntimeWiring.newRuntimeWiring()
            .type("Query"){
                    typeWiring -> typeWiring.dataFetcher("accounts",accountsFetcher)
            }
            .type("Account"){
                typeWiring -> typeWiring.dataFetcher("positions",positionFetcher)
            }
                .build()
        val schema = generator.makeExecutableSchema(typeRegistry,runtimeWiring)

        return  GraphQL.newGraphQL(schema).build()
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