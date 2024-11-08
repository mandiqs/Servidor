package br.pucpr.authserver.author.requests

import br.pucpr.authserver.author.Author
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class CreateAuthorRequest(
    @field:NotBlank
    val name: String,

    val birthDate: LocalDate?
) {
    fun toAuthor(): Author = Author(
        id = null,
        name = name,
        birthDate = birthDate
    )
}
