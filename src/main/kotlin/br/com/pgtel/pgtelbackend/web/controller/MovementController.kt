package br.com.pgtel.pgtelbackend.web.controller

import br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.create.CreateMovementUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.create.dto.CreateMovementInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.delete.DeleteMovementUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.delete.dto.DeleteMovementInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.findPageable.FindPageableMovementUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.findPageable.dto.FindPageableMovementInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.findPageable.dto.FindPageableMovementOutputUseCase
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/movements")
class MovementController(
    private val findMovementsPageableUseCase: FindPageableMovementUseCase,
    private val createMovementUseCase: CreateMovementUseCase,
    private val deleteMovementUseCase: DeleteMovementUseCase
) {

    @GetMapping
    fun findAll(params: MovementPageableRequestParams): FindPageableMovementOutputUseCase {
        return findMovementsPageableUseCase.execute(
            FindPageableMovementInputUseCase(
                params.page,
                params.size,
                params.sort,
                params.direction
            )
        )
    }

    @PostMapping
    fun create(@RequestBody request: CreateMovementRequest) {
        createMovementUseCase.execute(request.toUseCase())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) {
        deleteMovementUseCase.execute(DeleteMovementInputUseCase(id))
    }
}

data class MovementPageableRequestParams(
    val page: Int = 0,
    val size: Int = 10,
    val sort: String = "createdAt",
    val direction: String = "DESC"
)

data class CreateMovementRequest(
    val type: String,
    val productId: UUID,
    val unitPrice: Int?,
    val quantity: Int,
) {
    fun toUseCase() = CreateMovementInputUseCase(
        type = type,
        productId = productId,
        unitPrice = unitPrice,
        quantity = quantity,
    )
}