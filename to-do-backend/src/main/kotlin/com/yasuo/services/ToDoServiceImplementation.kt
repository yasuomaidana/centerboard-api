package com.yasuo.services

import com.yasuo.entities.ToDo
import com.yasuo.repositories.ToDoRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class ToDoServiceImplementation:ToDoService {
    @Inject
    lateinit var toDoRepository: ToDoRepository
    @Inject
    lateinit var authorService: AuthorService

    override fun getAll(): Iterable<ToDo?> {
        return toDoRepository.findAll()
    }

    override fun createToDo(title: String, username: String): ToDo {
        val author = authorService.findOrCreate(username)
        return toDoRepository.save(ToDo(title,author.id!!))
    }
}