package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.create

import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.Movement
import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.MovementType
import br.com.pgtel.pgtelbackend.modules.stock.domain.errors.MovementErrors
import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.MovementGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.create.dto.CreateMovementInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.create.dto.CreateMovementOutputUseCase

class CreateMovementUseCase(
    private val productGateway: ProductGateway,
    private val movementGateway: MovementGateway
) {
    fun execute(input: CreateMovementInputUseCase): CreateMovementOutputUseCase {
        val product =
            productGateway.findById(input.productId) ?: throw BusinessException(MovementErrors.PRODUCT_NOT_FOUND)

        val movement = Movement.create(
            type = MovementType.valueOf(input.type),
            quantity = input.quantity,
            product = product,
        )

        movementGateway.save(movement)
        productGateway.save(product)

        return CreateMovementOutputUseCase.fromDomain(
            movement
        )
    }
}