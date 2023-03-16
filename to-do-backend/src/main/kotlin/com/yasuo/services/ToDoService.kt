package com.yasuo.services

import com.yasuo.entities.ToDo
import javax.transaction.Transactional

interface ToDoService {
    fun getAll(): Iterable<ToDo?>
    @Transactional
    fun createToDo(title:String, username:String):ToDo

}