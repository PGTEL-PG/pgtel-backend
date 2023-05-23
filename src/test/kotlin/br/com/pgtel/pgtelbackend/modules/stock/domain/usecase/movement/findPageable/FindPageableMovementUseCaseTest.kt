package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.findPageable

import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.Movement
import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.MovementType
import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.Product
import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.MovementGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.findPageable.dto.FindPageableMovementInputUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import java.math.BigDecimal
import java.time.Instant
import java.util.*


class FindPageableMovementUseCaseTest {

    private val movementGateway: MovementGateway = mockk()
    private var useCase: FindPageableMovementUseCase = FindPageableMovementUseCase(movementGateway)

    @Test
    fun `should return an empty list when there are no movements`() {
        every { movementGateway.findMovementsPageable(any(), any(), any(), any()) } returns Page.empty()

        val input = FindPageableMovementInputUseCase(0, 10, "createdAt", "DESC")
        val output = useCase.execute(input)

        assert(output.totalItems == 0)
        assert(output.isLastPage)
        assert(output.content.isEmpty())
    }

    @Test
    fun `should return a list of movements when there are movements`() {
        val movement = Movement.create(
            id = UUID.randomUUID(),
            type = MovementType.OUT,
            product = Product.create(
                id = UUID.randomUUID(),
                name = "Product",
                picture = "picture",
                price = BigDecimal.TEN,
                quantity = 20,
                createdAt = Instant.now()
            ),
            quantity = 10,
            createdAt = Instant.now()
        )

        every { movementGateway.findMovementsPageable(any(), any(), any(), any()) } returns PageImpl(listOf(movement))
        val input = FindPageableMovementInputUseCase(0, 10, "createdAt", "DESC")
        val output = useCase.execute(input)

        assert(output.totalItems == 1)
        assert(output.isLastPage)
        assert(output.content.isNotEmpty())
    }

}