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
import jakarta.inject.Singleton
import java.io.BufferedReader
import java.io.InputStreamReader

@Factory
class GraphQLFactory {

    @Bean
    @Singleton
    fun graphQL(resourceResolver: ResourceResolver, accountsFetcher: AccountsFetcher):GraphQL{
        val parser = SchemaParser()
        val generator = SchemaGenerator()

         val typeRegistry = TypeDefinitionRegistry()
        typeRegistry.merge(parser.parse(
            BufferedReader(InputStreamReader(
                resourceResolver.getResourceAsStream("classpath:schema.graphqls").get()))))

        val runtimeWiring = RuntimeWiring.newRuntimeWiring()
            .type("Query"){typeWiring -> typeWiring.dataFetcher("accounts",accountsFetcher)
            }.build()
        val schema = generator.makeExecutableSchema(typeRegistry,runtimeWiring)

        return  GraphQL.newGraphQL(schema).build()
    }
}

@Singleton
class AccountsFetcher:DataFetcher<List<Account>>{
    override fun get(environment: DataFetchingEnvironment?): List<Account> {
        return listOf(
            Account("1","Savings","Hehe I don have savings"),
            Account("2","Expenses","I have a lot"),
            Account("3","Others","Mmmm mmm suspicious")
        )
    }

}