package br.pucpr.authserver.book.requests

import br.pucpr.authserver.book.Book
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class CreateBookRequest(
    @field:NotNull
    val id: Long = 0,
    @field:NotBlank
    val title: String?,
    @field:NotNull
    val publicationDate: LocalDate?
) {
    fun toBook(): Book = Book(
        id = null,
        title = title!!,
        publicationDate = publicationDate ?: LocalDate.now(),
        author = null
    )
}
