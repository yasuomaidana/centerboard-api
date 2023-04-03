package com.yasuo.controllers

import com.yasuo.entities.Author
import com.yasuo.repositories.AuthorRepository
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import jakarta.inject.Inject

@Controller("/author")
class AuthorController {
    @Inject
    lateinit var authorRepository:AuthorRepository
    @Get
    fun getAuthors():List<Author>{
        return authorRepository.findAll().map { author: Author? ->  author!!}
    }
}