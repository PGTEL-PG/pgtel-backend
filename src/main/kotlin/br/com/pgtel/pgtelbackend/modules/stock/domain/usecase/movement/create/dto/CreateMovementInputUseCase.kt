package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.create.dto

import java.util.*

data class CreateMovementInputUseCase(
    val type: String,
    val productId: UUID,
    val quantity: Int
)