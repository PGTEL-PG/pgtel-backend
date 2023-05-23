package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.findPageable

import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.MovementGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.findPageable.dto.FindPageableMovementInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.findPageable.dto.FindPageableMovementOutputUseCase

class FindPageableMovementUseCase(
    private val movementGateway: MovementGateway
) {
    fun execute(input: FindPageableMovementInputUseCase): FindPageableMovementOutputUseCase {
        return movementGateway.findMovementsPageable(input.page, input.size, input.sort, input.direction)
            .map { movement ->
                FindPageableMovementOutputUseCase.MovementOutputUseCase(
                    movement.id,
                    movement.type.value,
                    FindPageableMovementOutputUseCase.ProductOutputUseCase(
                        movement.product.id,
                        movement.product.name,
                        movement.product.picture,
                    ),
                    movement.quantity,
                    movement.createdAt
                )
            }.let {
            FindPageableMovementOutputUseCase(
                it.totalElements.toInt(),
                it.isLast,
                it.content
            )
        }
    }

}