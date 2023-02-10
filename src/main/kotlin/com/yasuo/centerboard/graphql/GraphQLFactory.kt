package com.yasuo.centerboard.graphql

import com.yasuo.centerboard.graphql.schema.Account
import com.yasuo.centerboard.graphql.schema.Position
import com.yasuo.centerboard.graphql.schema.Quote
import com.yasuo.centerboard.quotes.QuoteService
import com.yasuo.centerboard.repositories.AccountRepository
import graphql.GraphQL
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import graphql.schema.idl.RuntimeWiring.newRuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeDefinitionRegistry
import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.core.io.ResourceResolver
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.InputStreamReader

@Factory
class GraphQLFactory {

    @Inject
    lateinit var accountRepository: AccountRepository
    @Inject
    lateinit var quotesFetcher: QuotesFetcher

    @Bean
    @Singleton
    fun graphQL(resourceResolver: ResourceResolver):GraphQL{
        val schemaParser = SchemaParser()
        val typeDefinitionRegistry = TypeDefinitionRegistry()
        val graphqlSchema = resourceResolver.getResourceAsStream("classpath:schema.graphqls")

        typeDefinitionRegistry.merge(schemaParser.parse(BufferedReader(InputStreamReader(graphqlSchema.get()))))
        val runtimeWiring = newRuntimeWiring()
            .type(newTypeWiring("Query")
                .dataFetcher("accounts",AccountsFetcher())
                .dataFetcher("quotes",quotesFetcher.getQuotes())
                .dataFetcher("quoteBySymbol",quotesFetcher.getBySymbol())
            )
            .type(newTypeWiring("Account").dataFetcher("positions",PositionFetcher()))
            .build()
        val schemaGenerator = SchemaGenerator()
        val graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry,runtimeWiring)

        return  GraphQL.newGraphQL(graphQLSchema).build()
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
class QuotesFetcher{
    @Inject
    lateinit var quoteService: QuoteService
    fun getQuotes(): DataFetcher<List<Quote>> {
        return DataFetcher { quoteService.getQuotes() }
    }

    fun getBySymbol():DataFetcher<Quote>{
        return DataFetcher { environment: DataFetchingEnvironment ->
            val symbol: String = environment.getArgument("symbol")
            runBlocking { quoteService.getQuoteBySymbol(symbol) }
        }
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