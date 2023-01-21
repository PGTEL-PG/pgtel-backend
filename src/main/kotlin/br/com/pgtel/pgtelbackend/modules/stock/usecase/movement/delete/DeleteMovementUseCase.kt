package br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.delete

import br.com.pgtel.pgtelbackend.modules.stock.domain.MovementType
import br.com.pgtel.pgtelbackend.modules.stock.errors.MovementErrors
import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import br.com.pgtel.pgtelbackend.modules.stock.gateway.MovementGateway
import br.com.pgtel.pgtelbackend.modules.stock.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.delete.dto.DeleteMovementInputUseCase

class DeleteMovementUseCase(
    private val movementGateway: MovementGateway,
    private val productGateway: ProductGateway
) {
    fun execute(input: DeleteMovementInputUseCase) {
        movementGateway.findById(input.id).let { movement ->
            if (movement == null) return



            val product = movement.product
            if (movement.type == MovementType.OUT) {
                product.increaseStock(movement.quantity)
            } else {
                if (!product.hasStock(movement.quantity)) {
                    throw BusinessException(MovementErrors.MOVEMENT_CANNOT_BE_NEGATIVE_QUANTITY)
                }
                product.decreaseStock(movement.quantity)
            }
            movementGateway.delete(movement.id)
            productGateway.save(product)
        }
    }
}