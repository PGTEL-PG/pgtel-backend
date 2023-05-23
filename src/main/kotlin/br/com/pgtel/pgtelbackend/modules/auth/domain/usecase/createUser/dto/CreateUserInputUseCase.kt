package br.com.pgtel.pgtelbackend.modules.auth.domain.usecase.createUser.dto

data class CreateUserInputUseCase(
    val name: String,
    val email: String,
    val password: String,
)