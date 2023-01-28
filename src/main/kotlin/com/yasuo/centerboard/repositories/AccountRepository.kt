package com.yasuo.centerboard.repositories

import com.yasuo.centerboard.graphql.schema.Account
import graphql.schema.DataFetchingEnvironment
import jakarta.inject.Singleton

@Singleton
class AccountRepository{
    fun getAccounts(): List<Account> {
        return listOf(
            Account("1","Savings","Hehe I don have savings"),
            Account("2","Expenses","I have a lot"),
            Account("3","Others","Mmmm mmm suspicious")
        )
    }
}