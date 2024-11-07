package br.pucpr.authserver.library

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonIgnore

@Entity
data class Author(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonIgnore
    val books: List<Book> = mutableListOf()
)
