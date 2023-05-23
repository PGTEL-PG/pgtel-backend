package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.create.dto

import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.Movement
import java.time.Instant
import java.util.*

data class CreateMovementOutputUseCase(
    val id: UUID,
    val type: String,
    val quantity: Int,
    val product: CreateMovementProductOutputUseCase,
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
                createdAt = movement.createdAt
            )
        }
    }
}