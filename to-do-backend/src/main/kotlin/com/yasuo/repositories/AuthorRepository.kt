package com.yasuo.repositories

import com.yasuo.entities.Author
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.repository.CrudRepository
@JdbcRepository
abstract class AuthorRepository:CrudRepository<Author, Long> {
    abstract fun findByUsername(username: String): Author?
    abstract fun findByIdIn(ids: Collection<Long>):Collection<Author>

    fun findOrCreate(username: String): Author{
        return findByUsername(username) ?: save(Author(username))
    }
}