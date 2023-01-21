package br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.create.dto

import br.com.pgtel.pgtelbackend.modules.stock.domain.Movement
import java.math.BigDecimal
import java.time.Instant
import java.util.*

data class CreateMovementOutputUseCase(
    val id: UUID,
    val type: String,
    val quantity: Int,
    val product: CreateMovementProductOutputUseCase,
    val unitPrice: Int?,
    val createdAt: Instant
) {
    data class CreateMovementProductOutputUseCase(
        val id: UUID,
        val name: String,
        val picture: String? = null,
    )

    companion object {
        fun fromDomain(movement: Movement): CreateMovementOutputUseCase {
            return CreateMovementOutputUseCase(
                id = movement.id,
                type = movement.type.name,
                quantity = movement.quantity,
                product = CreateMovementProductOutputUseCase(
                    id = movement.product.id,
                    name = movement.product.name,
                    picture = movement.product.picture
                ),
                unitPrice = movement.unitPrice?.multiply(BigDecimal(100))?.intValueExact(),
                createdAt = movement.createdAt
            )
        }
    }
}