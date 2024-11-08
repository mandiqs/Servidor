package br.pucpr.authserver.author

import br.pucpr.authserver.author.Author
import br.pucpr.authserver.author.AuthorService
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