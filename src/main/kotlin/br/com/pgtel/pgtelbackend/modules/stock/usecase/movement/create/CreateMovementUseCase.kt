package br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.create

import br.com.pgtel.pgtelbackend.modules.stock.domain.Movement
import br.com.pgtel.pgtelbackend.modules.stock.domain.MovementType
import br.com.pgtel.pgtelbackend.modules.stock.errors.MovementErrors
import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import br.com.pgtel.pgtelbackend.modules.stock.gateway.MovementGateway
import br.com.pgtel.pgtelbackend.modules.stock.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.create.dto.CreateMovementInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.create.dto.CreateMovementOutputUseCase
import java.math.BigDecimal

class CreateMovementUseCase(
    private val productGateway: ProductGateway,
    private val movementGateway: MovementGateway
) {
    fun execute(input: CreateMovementInputUseCase): CreateMovementOutputUseCase {
        val product =
            productGateway.findById(input.productId) ?: throw BusinessException(MovementErrors.PRODUCT_NOT_FOUND)

        val type = enumValueOf<MovementType>(input.type.trim())

        if (MovementType.OUT == type) {
            if (!product.hasStock(input.quantity)) throw BusinessException(MovementErrors.MOVEMENT_STOCK_IS_NOT_AVAILABLE)
            product.decreaseStock(input.quantity)
        } else {
            product.increaseStock(input.quantity)
        }

        val movement = Movement.create(
            type = MovementType.valueOf(input.type),
            quantity = input.quantity,
            product = product,
            unitPrice = input.unitPrice?.toBigDecimal()?.divide(BigDecimal(100))
        )

        movementGateway.save(movement)
        productGateway.save(product)

        return CreateMovementOutputUseCase.fromDomain(
            movement
        )
    }
}