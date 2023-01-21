package br.com.pgtel.pgtelbackend.modules.auth.infrastructure.entity

import br.com.pgtel.pgtelbackend.modules.auth.domain.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant
import java.util.UUID

@Entity(name = "tb_user")
class UserEntity(

    @Id
    @Column(name = "cd_user")
    val id: UUID,
    @Column(name = "nm_user")
    val name: String,
    @Column(name = "cd_email", unique = true)
    val email: String,
    @Column(name = "cd_password")
    val password: String,
    @Column(name = "dt_created_at")
    val createdAt: Instant,
    @Column(name = "dt_updated_at")
    val updatedAt: Instant,
    @Column(name = "dt_deleted_at")
    val deletedAt: Instant? = null,
) {
    companion object {
        fun fromDomain(user: User): UserEntity {
            return UserEntity(
                id = user.id,
                name = user.name,
                email = user.email,
                password = user.password,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt,
                deletedAt = user.deletedAt,
            )
        }
    }
}
