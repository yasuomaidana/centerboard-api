package com.yasuo.repositories

import com.yasuo.entities.ToDo
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.repository.PageableRepository

@JdbcRepository//(dialect = POSTGRES) not added since we already defined it in application.yml
interface ToDoRepository: PageableRepository<ToDo, Long>