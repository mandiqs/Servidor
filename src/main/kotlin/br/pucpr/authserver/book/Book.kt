package br.pucpr.authserver.book

import jakarta.persistence.*
import br.pucpr.authserver.author.Author
import java.time.LocalDate


@Entity
data class Book(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var title: String,

    var publicationDate: LocalDate?,

    @ManyToOne
    @JoinColumn(name = "author_id")
    var author: Author? = null
)