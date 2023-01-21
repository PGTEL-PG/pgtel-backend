package br.com.pgtel.pgtelbackend.modules.auth.usecase.createUser.dto

import java.time.Instant
import java.util.UUID

data class CreateUserOutputUseCase(
    val id: UUID,
    val name: String,
    val email: String,
    val createdAt: Instant,
)