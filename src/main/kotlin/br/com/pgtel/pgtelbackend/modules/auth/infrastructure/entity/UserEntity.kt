package br.com.pgtel.pgtelbackend.modules.auth.infrastructure.entity

import br.com.pgtel.pgtelbackend.modules.auth.domain.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant
import java.util.*

@Entity(name = "tb_user")
class UserEntity(

    @Id
    @Column(name = "cd_user")
    val id: UUID,
    @Column(name = "nm_user")
    val name: String,
    @Column(name = "url_avatar")
    val avatar: String,
    @Column(name = "cd_email", unique = true)
    private val email: String,
    @Column(name = "cd_password")
    private val password: String,
    @Column(name = "dt_created_at")
    val createdAt: Instant,
    @Column(name = "dt_updated_at")
    val updatedAt: Instant,
    @Column(name = "dt_deleted_at")
    val deletedAt: Instant? = null,
) : UserDetails {
    companion object {
        fun fromDomain(user: User): UserEntity {
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

    fun toDomain(): User {
        return User.create(
            id = id,
            name = name,
            email = email,
            password = password,
            avatar = avatar,
            createdAt = createdAt,
            updatedAt = updatedAt,
            deletedAt = deletedAt,
        )
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.email
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
}
