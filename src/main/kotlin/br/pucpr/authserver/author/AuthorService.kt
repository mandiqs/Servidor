package br.pucpr.authserver.author

import br.pucpr.authserver.errors.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AuthorService(private val authorRepository: AuthorRepository) {

    fun createAuthor(author: Author): Author {
        return authorRepository.save(author)
    }

    fun getAuthor(id: Long): Author {
        return authorRepository.findByIdOrNull(id)
                ?: throw NotFoundException("No author found with ID $id")
    }

}