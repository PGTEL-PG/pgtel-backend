package br.com.pgtel.pgtelbackend.modules.auth.domain.entities

import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import br.com.pgtel.pgtelbackend.modules.auth.domain.errors.UserErrors
import java.time.Instant
import java.util.*

class User private constructor(
    val id: UUID,
    val name: String,
    val email: String,
    val avatar: String,
    var token: String?,
    val password: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?
) {
    companion object {
        fun create(name: String, email: String, password: String): User {
            val user = User(
                id = UUID.randomUUID(),
                name = name,
                email = email,
                avatar = "",
                password = password,
                createdAt = Instant.now(),
                updatedAt = Instant.now(),
                token = null,
                deletedAt = null
            )
            user.validate()
            return user
        }

        fun create(
            id: UUID,
            name: String,
            email: String,
            password: String,
            avatar: String,
            createdAt: Instant,
            updatedAt: Instant,
            deletedAt: Instant?
        ): User {
            val user = User(
                id = id,
                name = name,
                email = email,
                avatar = avatar,
                password = password,
                createdAt = createdAt,
                updatedAt = updatedAt,
                token = null,
                deletedAt = deletedAt
            )
            user.validate()
            return user
        }
    }

    fun token(token: String) {
        this.token = token
    }

    private fun validate() {
        if (name.isBlank()) {
            throw BusinessException(UserErrors.NAME_IS_BLANK)
        }
        if (email.isBlank()) {
            throw BusinessException(UserErrors.EMAIL_IS_BLANK)
        }
        if (password.isBlank()) {
            throw BusinessException(UserErrors.PASSWORD_IS_BLANK)
        }
    }
}