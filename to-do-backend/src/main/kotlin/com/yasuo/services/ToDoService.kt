package com.yasuo.services

import com.yasuo.entities.ToDo

interface ToDoService {
    fun getAll(): Iterable<ToDo?>

}