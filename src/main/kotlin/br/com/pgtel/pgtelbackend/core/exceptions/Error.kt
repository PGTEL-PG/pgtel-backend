package br.com.pgtel.pgtelbackend.core.exceptions

data class Error(
    val code: String,
    val message: String,
)