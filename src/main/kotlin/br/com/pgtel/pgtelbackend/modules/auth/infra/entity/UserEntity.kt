package br.com.pgtel.pgtelbackend.modules.auth.infra.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant
import java.util.*
import br.com.pgtel.pgtelbackend.modules.auth.domain.entities.User as DomainEntitiesUser

@Entity(name = "tb_user")
class UserEntity : UserDetails {
    @Id
    @Column(name = "cd_user")
    var id: UUID? = null

    @Column(name = "nm_user")
    var name: String? = null

    @Column(name = "url_avatar")
    var avatar: String? = null

    @Column(name = "cd_email", unique = true)
    private var email: String? = null

    @Column(name = "cd_password")
    private var password: String? = null

    @Column(name = "dt_created_at")
    var createdAt: Instant = Instant.now()

    @Column(name = "dt_updated_at")
    var updatedAt: Instant = Instant.now()

    @Column(name = "dt_deleted_at")
    var deletedAt: Instant? = null


    constructor(
        id: UUID?,
        name: String?,
        avatar: String?,
        email: String?,
        password: String?,
        createdAt: Instant,
        updatedAt: Instant,
        deletedAt: Instant?
    ) {
        this.id = id
        this.name = name
        this.avatar = avatar
        this.email = email
        this.password = password
        this.createdAt = createdAt
        this.updatedAt = updatedAt
        this.deletedAt = deletedAt
    }

    constructor() {

    }


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getPassword(): String {
        return this.password!!
    }

    override fun getUsername(): String {
        return this.email!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun toDomain(): DomainEntitiesUser {
        return DomainEntitiesUser.create(
            id = id!!,
            name = name!!,
            email = email!!,
            password = password!!,
            avatar = avatar!!,
            createdAt = createdAt,
            updatedAt = updatedAt!!,
            deletedAt = deletedAt,
        )
    }

    companion object {
        fun fromDomain(user: DomainEntitiesUser): UserEntity {
            return UserEntity(
                id = user.id,
                name = user.name,
                email = user.email,
                avatar = user.avatar,
                password = user.password,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt,
                deletedAt = user.deletedAt,
            )
        }
    }
}