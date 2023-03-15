package com.yasuo.mappers

import com.yasuo.entities.ToDo
import com.yasuo.graphql.schemas.AuthorSchema
import com.yasuo.graphql.schemas.ToDoSchema
import com.yasuo.services.AuthorService
import jakarta.inject.Inject
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "jsr330")
abstract class ToDoMapper {

    @Inject
    lateinit var authorService: AuthorService
    @Mapping(target="author", expression ="java(getAuthorFromId(toDo.getAuthorId()))")
    abstract fun convertToToDoSchema(toDo:ToDo):ToDoSchema

    fun getAuthorFromId(authorId: Long): AuthorSchema {
        val author = authorService.findByIdIn(listOf(authorId)).first()
        val userName = author.username.orEmpty()
        return AuthorSchema(authorId.toInt(), userName)
    }
}