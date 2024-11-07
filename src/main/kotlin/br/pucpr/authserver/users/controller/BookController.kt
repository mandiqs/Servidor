package br.pucpr.authserver.library.controller

import br.pucpr.authserver.library.Book
import br.pucpr.authserver.library.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(private val bookService: BookService) {

    //Endpoint criar livro
    @PostMapping
    fun createBook(@RequestBody book: Book): ResponseEntity<Book> {
        return ResponseEntity.ok(bookService.createBook(book))
    }

    //Endpoint procurar livro
    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: Long): ResponseEntity<Book> {
        return ResponseEntity.ok(bookService.getBookById(id))
    }

    //Endpoint atualizar livro
    @PutMapping("/{id}")
    fun updateBook(@PathVariable id: Long, @RequestBody book: Book): ResponseEntity<Book> {
        return ResponseEntity.ok(bookService.updateBook(id, book))
    }

    //Endpoint deletar livro --> adicionar autenticação para delete
    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: Long): ResponseEntity<Void> {
        bookService.deleteBook(id)
        return ResponseEntity.noContent().build()
    }

    //Endpoint vincular autor e livro
    @PutMapping("/{bookId}/author/{authorId}")
    fun assignAuthorToBook(
        @PathVariable bookId: Long,
        @PathVariable authorId: Long
    ): ResponseEntity<Book> {
        return ResponseEntity.ok(bookService.assignAuthor(bookId, authorId))
    }
}

