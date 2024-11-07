package br.pucpr.authserver.library

import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository
) {

    //Criar livro
    fun createBook(book: Book): Book {
        return bookRepository.save(book)
    }

    //Buscar livro
    fun getBookById(id: Long): Book {
        return bookRepository.findById(id).orElseThrow { Exception("Book not found") }
    }

    //Atualizar livro
    fun updateBook(id: Long, updatedBook: Book): Book {
        val book = bookRepository.findById(id).orElseThrow { Exception("Book not found") }
        book.title = updatedBook.title
        book.author = updatedBook.author
        return bookRepository.save(book)
    }

    //Deletar livro
    fun deleteBook(id: Long) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id)
        } else {
            throw Exception("Book not found")
        }
    }

    // Método para associar um autor a um livro
    fun assignAuthor(bookId: Long, authorId: Long): Book {
        val book = bookRepository.findById(bookId).orElseThrow { Exception("Book not found") }
        val author = authorRepository.findById(authorId).orElseThrow { Exception("Author not found") }

        book.author = author //Associa o autor ao livro
        return bookRepository.save(book) //Salva no bd
    }
}

