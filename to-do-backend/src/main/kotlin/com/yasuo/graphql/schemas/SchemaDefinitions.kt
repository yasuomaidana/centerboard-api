package com.yasuo.graphql.schemas

import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.yasuo.mappers.ToDoMapper
import com.yasuo.services.ToDoService
import jakarta.inject.Inject
import jakarta.inject.Singleton

@GraphQLName("Author")
data class AuthorSchema(val id:Int, val username:String)

@GraphQLName("ToDo")
data class ToDoSchema(val id:Int, val title: String, val completed:Boolean, val author: AuthorSchema)

@Suppress("unused")
@Singleton
class AuthorQuery{
    fun getAuthor():AuthorSchema{
        return AuthorSchema(1,"Hi")
    }
}

@Singleton
@Suppress("unused")
class ToDoQuery{
    @Inject
    lateinit var  toDoService: ToDoService
    @Inject
    lateinit var toDoMapper: ToDoMapper
    fun toDos():List<ToDoSchema>{

        return toDoService.getAll().map { toDo -> toDoMapper.convertToToDoSchema(toDo!!) }
    }
}

@Singleton
@Suppress("unused")
class ToDoMutation{
    @Inject
    lateinit var toDoService: ToDoService
    @Inject
    lateinit var toDoMapper: ToDoMapper
    fun createToDo(title: String, username: String):ToDoSchema{
        val savedToDo = toDoService.createToDo(title, username)
        return toDoMapper.convertToToDoSchema(savedToDo)
    }
}