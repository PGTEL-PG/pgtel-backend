package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.create

import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.Product
import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.MovementGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.create.dto.CreateMovementInputUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.util.*

class CreateMovementUseCaseTest {

    private val productGateway: ProductGateway = mockk()
    private val movementGateway: MovementGateway = mockk()
    private lateinit var createMovementUseCase: CreateMovementUseCase

    @BeforeEach
    fun setUp() {
        createMovementUseCase = CreateMovementUseCase(
            productGateway = productGateway,
            movementGateway = movementGateway
        )
    }

    @Test
    fun `should throw exception when product not found`() {

        every { productGateway.findById(any()) } returns null

        assertThrows<BusinessException> {
            createMovementUseCase.execute(
                CreateMovementInputUseCase(
                    productId = UUID.randomUUID(),
                    quantity = 10,
                    type = "ENTRY"
                )
            )
        }
    }

    @Test
    fun `should throw exception when type is invalid`() {

        every { productGateway.findById(any()) } returns mockk()

        assertThrows<IllegalArgumentException> {
            createMovementUseCase.execute(
                CreateMovementInputUseCase(
                    productId = UUID.randomUUID(),
                    quantity = 10,
                    type = "INVALID"
                )
            )
        }
    }

    @Test
    fun `should throw exception when quantity is invalid`() {

        every { productGateway.findById(any()) } returns mockk()

        assertThrows<BusinessException> {
            createMovementUseCase.execute(
                CreateMovementInputUseCase(
                    productId = UUID.randomUUID(),
                    quantity = -1,
                    type = "ENTRY"
                )
            )
        }
    }

    @Test
    fun `should throw exception when quantity is greater than stock`() {

        every { productGateway.findById(any()) } returns Product.create(
            name = "Product",
            price = BigDecimal.TEN,
            quantity = 5,
            minStock = null
        )

        assertThrows<BusinessException> {
            createMovementUseCase.execute(
                CreateMovementInputUseCase(
                    productId = UUID.randomUUID(),
                    quantity = 10,
                    type = "OUT"
                )
            )
        }
    }

    @Test
    fun `should save movement and update product`() {

        val product = Product.create(
            name = "Product",
            price = BigDecimal.TEN,
            quantity = 5,
            minStock = null
        )

        every { productGateway.findById(any()) } returns product
        every { movementGateway.save(any()) } returns mockk()
        every { productGateway.save(any()) } returns mockk()

        createMovementUseCase.execute(
            CreateMovementInputUseCase(
                productId = UUID.randomUUID(),
                quantity = 2,
                type = "OUT"
            )
        )
    }

}