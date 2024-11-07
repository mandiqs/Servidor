package br.pucpr.authserver.library

import jakarta.persistence.*

@Entity
data class Book(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var title: String,

    @ManyToOne
    @JoinColumn(name = "author_id")
    var author: Author? = null
)

