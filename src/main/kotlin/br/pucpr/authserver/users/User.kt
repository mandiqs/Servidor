package br.pucpr.authserver.users

import br.pucpr.authserver.roles.Role
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import jakarta.persistence.Transient

@Entity //entidade do banco de dados, mapeada em uma tabela com o mesmo nome da entidade
@Table(name = "TblUser")
class User(
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true)
    var email: String,

    var password: String,

    var name: String,

    @ManyToMany
    @JoinTable(
        name = "UserRole",
        joinColumns = [JoinColumn(name = "idUser")],
        inverseJoinColumns = [JoinColumn(name = "idRole")]
    )
    val roles: MutableSet<Role> = mutableSetOf()
) {
    @get:Transient
    val isAdmin: Boolean get() = roles.any { it.name == "ADMIN" }
}