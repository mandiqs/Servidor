package br.pucpr.authserver.author

import br.pucpr.authserver.errors.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import br.pucpr.authserver.book.BookRepository

@Service
class AuthorService(
    private val authorRepository: AuthorRepository,
    private val bookRepository: BookRepository){

    fun createAuthor(author: Author): Author {
        return authorRepository.save(author)

    }

    fun getAuthor(id: Long): Author {
        return authorRepository.findByIdOrNull(id)
                ?: throw NotFoundException("No author found with ID $id")
    }

    fun addBookToAuthor(authorId: Long, bookId: Long) {
        val author = authorRepository.findById(authorId)
            .orElseThrow { NotFoundException("Author not found with ID $authorId") }
        val book = bookRepository.findById(bookId)
            .orElseThrow { NotFoundException("Book not found with ID $bookId") }

        author.books.add(book)
        authorRepository.save(author)
    }

}