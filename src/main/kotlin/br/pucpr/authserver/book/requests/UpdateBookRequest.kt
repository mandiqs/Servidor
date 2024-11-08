package br.pucpr.authserver.book.requests

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class UpdateBookRequest(
    @field:NotBlank
    val title: String,

    @field:NotBlank
    val summary: String,

    @field:NotNull
    val publicationDate: LocalDate
)
