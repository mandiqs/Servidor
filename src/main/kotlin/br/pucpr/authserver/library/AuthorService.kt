package br.pucpr.authserver.library

import org.springframework.stereotype.Service

@Service
class AuthorService(private val authorRepository: AuthorRepository) {

    fun createAuthor(author: Author): Author {
        return authorRepository.save(author)
    }
}
