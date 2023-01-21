package br.com.pgtel.pgtelbackend.modules.stock.domain

import br.com.pgtel.pgtelbackend.modules.stock.errors.MovementErrors
import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

class Movement private constructor(
    val id: UUID,
    val type: MovementType,
    val quantity: Int,
    val product: Product,
    val unitPrice: BigDecimal?,
    val createdAt: Instant
) {

    companion object {
        fun create(
            type: MovementType,
            quantity: Int,
            product: Product,
            unitPrice: BigDecimal?,
        ): Movement {

            val movement = Movement(
                UUID.randomUUID(),
                type,
                quantity,
                product,
                unitPrice,
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
            unitPrice: BigDecimal?,
            createdAt: Instant
        ): Movement {
            val movement = Movement(
                id,
                type,
                quantity,
                product,
                unitPrice,
                createdAt
            )
            movement.validate()

            return movement
        }
    }

    private fun validate() {
        if (quantity <= 0) {
            throw BusinessException(MovementErrors.QUANTITY_INVALID)
        }

        if (unitPrice != null && unitPrice <= BigDecimal.ZERO) {
            throw BusinessException(MovementErrors.UNIT_PRICE_INVALID)
        }

        if (unitPrice == null && MovementType.OUT == type) {
            throw BusinessException(MovementErrors.UNIT_PRICE_IS_REQUIRED)
        }

    }

}

enum class MovementType(val value: String) {
    IN("IN"), OUT("OUT");
}
