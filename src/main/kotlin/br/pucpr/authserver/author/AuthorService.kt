package br.pucpr.authserver.author

import org.springframework.stereotype.Service

@Service
class AuthorService(private val authorRepository: AuthorRepository) {

    fun createAuthor(author: Author): Author {
        return authorRepository.save(author)
    }
}