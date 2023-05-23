package br.com.pgtel.pgtelbackend.modules.stock.infra.controllers

import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.MovementType
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.create.dto.CreateMovementInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.create.dto.CreateMovementOutputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.delete.DeleteMovementUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.delete.dto.DeleteMovementInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.findPageable.FindPageableMovementUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.findPageable.dto.FindPageableMovementInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.findPageable.dto.FindPageableMovementOutputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.infra.validators.EnumNamePattern
import br.com.pgtel.pgtelbackend.modules.stock.infra.validators.ValueOfEnum
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/movements")
class MovementController(
    private val findMovementsPageableUseCase: FindPageableMovementUseCase,
    private val createMovementUseCase: br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.create.CreateMovementUseCase,
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
    fun create(@Valid @RequestBody request: CreateMovementRequest): CreateMovementOutputUseCase {
        return createMovementUseCase.execute(request.toUseCase())
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

    @field:ValueOfEnum(MovementType::class, message = "Invalid type: ENTRY or OUT")
    val type: String,
    @field:Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Invalid product id")
    val productId: String,
    @field:Min(1)
    val quantity: Int,
) {
    fun toUseCase() = CreateMovementInputUseCase(
        type = type,
        productId = UUID.fromString(productId),
        quantity = quantity,
    )
}