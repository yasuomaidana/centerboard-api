package com.yasuo.mappers

import com.yasuo.entities.ToDo
import com.yasuo.graphql.schemas.ToDoSchema
import jakarta.inject.Inject
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "jsr330")
abstract class ToDoMapper {

    @Inject
    lateinit var authorMapper: AuthorMapper
    @Mapping(target="author", expression ="java(authorMapper.fromIdToAuthorSchema((toDo.getAuthorId())))")
    abstract fun convertToToDoSchema(toDo:ToDo):ToDoSchema
}