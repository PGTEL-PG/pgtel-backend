package br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.findPageable.dto

import java.time.Instant
import java.util.*

data class FindPageableMovementOutputUseCase(
    val totalItems: Int,
    val isLastPage: Boolean,
    val content: List<MovementOutputUseCase>
) {
    data class MovementOutputUseCase(
        val id: UUID,
        val type: String,
        val product: ProductOutputUseCase,
        val unitPrice: Int?,
        val quantity: Int,
        val createdAt: Instant
    )

    data class ProductOutputUseCase(
        val id: UUID,
        val name: String,
        val picture: String,
        val unitName: String,
    )
}
