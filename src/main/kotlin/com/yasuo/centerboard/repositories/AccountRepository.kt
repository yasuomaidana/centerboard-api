package com.yasuo.centerboard.repositories

import com.yasuo.centerboard.graphql.schema.Account
import com.yasuo.centerboard.graphql.schema.Position
import jakarta.inject.Singleton

@Singleton
class AccountRepository{
    fun getAccounts(): List<Account> {
        return listOf(
            Account("1","Savings","Hehe I don have savings",false,listOf(Position("1","APPL","22","1.2"))),
            Account("2","Expenses","I have a lot", positions =listOf(Position("2","AMZ","10","3.2")) ),
            Account("3","Others","Mmmm mmm suspicious", positions =listOf(Position("1","ALF","10","4.2")))
        )
    }
}