package com.yasuo.services

import com.yasuo.entities.ToDo
import com.yasuo.repositories.ToDoRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import javax.transaction.Transactional

@Singleton
class ToDoServiceImplementation:ToDoService {
    @Inject
    lateinit var toDoRepository: ToDoRepository

    @Transactional
    override fun getAll(): Iterable<ToDo?> {
        return toDoRepository.findAll()
    }
}