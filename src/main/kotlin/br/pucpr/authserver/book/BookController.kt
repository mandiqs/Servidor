package br.pucpr.authserver.book

import br.pucpr.authserver.book.requests.CreateBookRequest
import br.pucpr.authserver.book.requests.UpdateBookRequest
import br.pucpr.authserver.users.SortDir
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(
    private val bookService: BookService
) {
    @PostMapping
    fun insert(@RequestBody @Valid request: CreateBookRequest): ResponseEntity<Book> =
        bookService.insert(request.toBook())
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun list(@RequestParam(required = false) sortDir: String?): ResponseEntity<List<Book>> =
        bookService.list(SortDir.getByName(sortDir))
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Book> =
        bookService.getBook(id)
            .let { ResponseEntity.ok(it) }

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid request: UpdateBookRequest): ResponseEntity<Book> =
        bookService.update(id, request)
            .let { ResponseEntity.ok(it) }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        bookService.delete(id)
            .let { ResponseEntity.noContent().build() }
}
