package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.delete

import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.MovementType
import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.MovementGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.delete.dto.DeleteMovementInputUseCase

class DeleteMovementUseCase(
    private val movementGateway: MovementGateway,
    private val productGateway: ProductGateway
) {
    fun execute(input: DeleteMovementInputUseCase) {
        movementGateway.findById(input.id).let { movement ->
            if (movement == null) return


//todo: rever esse cenario
            val product = movement.product
            if (movement.type == MovementType.OUT) {
                product.increaseStock(movement.quantity)
            } else {
//                if (!product.hasStock(movement.quantity)) {
//                    throw BusinessException(MovementErrors.MOVEMENT_CANNOT_BE_NEGATIVE_QUANTITY)
//                }
                product.decreaseStock(movement.quantity)
            }
            movementGateway.delete(movement.id)
            productGateway.save(product)
        }
    }
}