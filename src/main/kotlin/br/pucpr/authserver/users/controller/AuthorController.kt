package br.pucpr.authserver.library.controller

import br.pucpr.authserver.library.Author
import br.pucpr.authserver.library.AuthorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/authors")
class AuthorController(private val authorService: AuthorService) {

    @PostMapping
    fun createAuthor(@RequestBody author: Author): ResponseEntity<Author> {
        return ResponseEntity.ok(authorService.createAuthor(author))
    }
}
