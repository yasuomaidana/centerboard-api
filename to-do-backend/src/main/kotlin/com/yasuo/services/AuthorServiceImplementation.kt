package com.yasuo.services

import com.yasuo.entities.Author
import com.yasuo.repositories.AuthorRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class AuthorServiceImplementation:AuthorService {
    @Inject
    lateinit var authorRepository: AuthorRepository
    override fun findByUsername(username: String): Author? {
        return authorRepository.findByUsername(username)
    }

    override fun findByIdIn(ids: Collection<Long>): Collection<Author> {
        return authorRepository.findByIdIn(ids)
    }

    override fun findOrCreate(username: String): Author {
        return authorRepository.findByUsername(username) ?: authorRepository.save(Author(username))
    }
}