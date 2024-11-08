package br.pucpr.authserver.author.requests

import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class UpdateAuthorRequest(
    @field:NotBlank
    val name: String,

    val birthDate: LocalDate?
)
