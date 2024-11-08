package br.pucpr.authserver.author

import jakarta.persistence.*
import br.pucpr.authserver.book.Book
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate


@Entity
data class Author(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    val birthDate: LocalDate? = null,

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonIgnore
    val books: List<Book> = mutableListOf()
)