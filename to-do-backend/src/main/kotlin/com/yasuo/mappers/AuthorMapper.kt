package com.yasuo.mappers

import com.yasuo.entities.Author
import com.yasuo.graphql.schemas.AuthorSchema
import com.yasuo.services.AuthorService
import jakarta.inject.Inject
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "jsr330")
abstract class AuthorMapper {
    @Inject
    lateinit var authorService: AuthorService

    @Mapping(target="id", expression ="java(author.getId().intValue())")
    abstract fun convertToAuthorSchema(author: Author): AuthorSchema

    fun fromIdToAuthorSchema(authorId: Long): AuthorSchema {
        val author = authorService.findByIdIn(listOf(authorId)).first()
        return convertToAuthorSchema(author)
    }
}