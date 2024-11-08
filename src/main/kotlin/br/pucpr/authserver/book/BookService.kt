package br.pucpr.authserver.book

import br.pucpr.authserver.book.requests.CreateBookRequest
import br.pucpr.authserver.book.requests.UpdateBookRequest
import br.pucpr.authserver.errors.NotFoundException
import br.pucpr.authserver.users.SortDir
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {
    fun insert(book: Book): Book {
        return bookRepository.save(book)
        //inserir lógica caso já exista o livro
    }

    fun list(sortDir: SortDir?): List<Book> {
        return when (sortDir) {
            SortDir.ASC -> bookRepository.findAll(Sort.by("id"))
            SortDir.DESC -> bookRepository.findAll(Sort.by("id").descending())
            else -> bookRepository.findAll()
        }
    }

    fun getBook(id: Long): Book {
        return bookRepository.findByIdOrNull(id) ?: throw NotFoundException("Livro não encontrado com esse id!")
    }

    fun update(id: Long, request: UpdateBookRequest): Book {
        val existingBook = getBook(id)
        existingBook.title = request.title
        existingBook.publicationDate = request.publicationDate
        return bookRepository.save(existingBook)
    }

    fun delete(id: Long) {
        val book = getBook(id)
        bookRepository.delete(book)
    }
}
