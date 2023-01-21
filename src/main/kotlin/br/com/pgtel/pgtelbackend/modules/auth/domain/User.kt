package br.com.pgtel.pgtelbackend.modules.auth.domain

import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import br.com.pgtel.pgtelbackend.modules.auth.errors.UserErrors
import java.time.Instant
import java.util.*

class User private constructor(
    val id: UUID,
    val name: String,
    val email: String,
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
                password = password,
                createdAt = Instant.now(),
                updatedAt = Instant.now(),
                deletedAt = null
            )
            user.validate()
            return user
        }
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