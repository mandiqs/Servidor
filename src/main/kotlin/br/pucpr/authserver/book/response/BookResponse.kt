package br.pucpr.authserver.book.response

import br.pucpr.authserver.book.Book
import java.time.LocalDate

data class BookResponse(
    val id: Long,
    val title: String,
    val publicationDate: LocalDate
) {
    constructor(book: Book) : this(
        id = book.id!!,
        title = book.title,
        publicationDate = book.publicationDate!!
    )
}
