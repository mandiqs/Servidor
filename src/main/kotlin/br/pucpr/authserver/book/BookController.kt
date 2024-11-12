package br.pucpr.authserver.book

import br.pucpr.authserver.book.requests.CreateBookRequest
import br.pucpr.authserver.book.requests.UpdateBookRequest
import br.pucpr.authserver.book.response.BookResponse
import br.pucpr.authserver.errors.BadRequestException
import br.pucpr.authserver.users.SortDir
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(
    private val bookService: BookService
) {
    @PostMapping
    fun insert(@RequestBody @Valid request: CreateBookRequest): ResponseEntity<BookResponse> =
        bookService.insert(request.toBook())
            .let { BookResponse(it) }
            .let { ResponseEntity.status(CREATED).body(it) }

    @GetMapping
    fun list(@RequestParam(required = false) sortDir: String?): ResponseEntity<List<Book>> =
        bookService.list(
            SortDir.getByName(sortDir) ?: throw BadRequestException("Sort inválido!")
        )
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Book> =
        bookService.getBook(id)
            .let { ResponseEntity.ok(it) }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "AuthServer")
    fun update(@PathVariable id: Long, @RequestBody @Valid request: UpdateBookRequest): ResponseEntity<Book> =
            bookService.update(id, request)
                    .let { ResponseEntity.ok(it) }

    @SecurityRequirement(name = "AuthServer")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        bookService.delete(id)
        return ResponseEntity.noContent().build()
    }
}




