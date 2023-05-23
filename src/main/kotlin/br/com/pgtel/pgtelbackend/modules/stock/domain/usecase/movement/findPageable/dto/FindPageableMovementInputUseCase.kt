package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.findPageable.dto

data class FindPageableMovementInputUseCase(
    val page: Int,
    val size: Int,
    val sort: String,
    val direction: String
)
