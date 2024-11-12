package br.pucpr.authserver.author

import br.pucpr.authserver.author.Author
import br.pucpr.authserver.author.AuthorService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.security.SecurityRequirement

@RestController
@RequestMapping("/api/authors")
class AuthorController(private val authorService: AuthorService) {

    private val logger = LoggerFactory.getLogger(AuthorController::class.java)

    @SecurityRequirement(name = "AuthServer")
    @PreAuthorize("permitAll()")
    @PostMapping
    fun createAuthor(@RequestBody author: Author): ResponseEntity<Author> {
        logger.debug("Entering createAuthor method in AuthorController")

        // Check if the user is authenticated and log authentication details
        val auth: Authentication? = SecurityContextHolder.getContext().authentication
        if (auth == null || !auth.isAuthenticated) {
            logger.warn("Unauthorized attempt to access createAuthor endpoint without valid JWT")
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
        logger.debug("Authenticated user: {}", auth.name)

        return ResponseEntity.ok(authorService.createAuthor(author))
    }

    @SecurityRequirement(name = "AuthServer")
    @PreAuthorize("permitAll()")
    @PostMapping("/{authorId}/books/{bookId}")
    fun addBookToAuthor(@PathVariable authorId: Long, @PathVariable bookId: Long): ResponseEntity<Void> {
        logger.debug("Adding book with ID {} to author with ID {}", bookId, authorId)

        authorService.addBookToAuthor(authorId, bookId)
        return ResponseEntity.noContent().build()
    }
}
