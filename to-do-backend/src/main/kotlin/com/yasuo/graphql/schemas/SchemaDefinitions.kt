package com.yasuo.graphql.schemas

import com.expediagroup.graphql.generator.annotations.GraphQLName
import jakarta.inject.Singleton

@GraphQLName("Author")
data class AuthorSchema(val id:Int, val username:String)

@GraphQLName("ToDo")
data class ToDoSchema(val id:Int, val title: String, val completed:Boolean, val author: AuthorSchema)

@Singleton
class AuthorQuery{
    fun getAuthor():AuthorSchema{
        return AuthorSchema(1,"Hi")
    }
}