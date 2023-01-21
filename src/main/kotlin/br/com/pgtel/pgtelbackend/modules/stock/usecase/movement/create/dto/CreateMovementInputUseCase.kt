package br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.create.dto

import java.util.*

data class CreateMovementInputUseCase(
    val type: String,
    val productId: UUID,
    val unitPrice: Int?,
    val quantity: Int
)