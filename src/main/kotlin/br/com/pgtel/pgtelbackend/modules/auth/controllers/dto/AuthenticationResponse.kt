package br.com.pgtel.pgtelbackend.modules.auth.controllers.dto

data class AuthenticationRequest(
    val username: String,
    val password: String,
)
