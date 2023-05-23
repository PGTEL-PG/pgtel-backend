package br.com.pgtel.pgtelbackend.modules.stock.domain.entities

import br.com.pgtel.pgtelbackend.modules.stock.domain.errors.MovementErrors
import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import java.time.Instant
import java.util.UUID

class Movement private constructor(
    val id: UUID,
    val type: MovementType,
    val quantity: Int,
    val product: Product,
    val createdAt: Instant
) {

    companion object {
        fun create(
            type: MovementType,
            quantity: Int,
            product: Product,
        ): Movement {

            val movement = Movement(
                UUID.randomUUID(),
                type,
                quantity,
                product,
                Instant.now()
            )

            movement.validate()

            return movement
        }

        fun create(
            id: UUID,
            type: MovementType,
            quantity: Int,
            product: Product,
            createdAt: Instant
        ): Movement {

            return Movement(
                id,
                type,
                quantity,
                product,
                createdAt
            )
        }
    }

    private fun validate() {
        if (quantity <= 0) {
            throw BusinessException(MovementErrors.QUANTITY_INVALID)
        }
        if (type == MovementType.OUT) {
            product.decreaseStock(quantity)
        }
        if (type == MovementType.ENTRY) {
            product.increaseStock(quantity)
        }
    }

}

enum class MovementType(val value: String) {
    ENTRY("ENTRY"), OUT("OUT");
}
