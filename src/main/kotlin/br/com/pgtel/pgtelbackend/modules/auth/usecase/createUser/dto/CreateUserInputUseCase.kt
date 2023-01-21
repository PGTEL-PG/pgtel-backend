package br.com.pgtel.pgtelbackend.modules.auth.usecase.createUser.dto

data class CreateUserInputUseCase(
    val name: String,
    val email: String,
    val password: String,
)