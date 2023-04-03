package com.yasuo.services

import com.yasuo.entities.Author

interface AuthorService {
    fun findByUsername(username: String): Author?
    fun findByIdIn(ids: Collection<Long>):Collection<Author>
    fun findOrCreate(username: String): Author
    fun getAll(): Collection<Author>
}