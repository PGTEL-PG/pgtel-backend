package br.com.pgtel.pgtelbackend.modules.auth.infra.controllers.dto

data class AuthenticationRequest(
    val username: String,
    val password: String,
)
