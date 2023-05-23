package br.com.pgtel.pgtelbackend.modules.stock.domain

import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.Movement
import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.MovementType
import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.Product
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.util.*

class MovementTest {

    @Test
    fun `should create a new movement`() {
        assertDoesNotThrow {
            Movement.create(
                MovementType.ENTRY,
                10,
                Product.create(
                    name = "Product 1",
                    price = BigDecimal(10),
                    quantity = 10,
                    minStock = 5,
                ),
            )

            Movement.create(
                id = UUID.randomUUID(),
                type = MovementType.ENTRY,
                quantity = 10,
                product = Product.create(
                    name = "Product 1",
                    price = BigDecimal(10),
                    quantity = 10,
                    minStock = 5,
                ),
                createdAt = Date().toInstant()
            )
        }
    }

    @Test
    fun `should not create a new movement with invalid quantity`() {
        assertThrows<BusinessException> {
            Movement.create(
                MovementType.ENTRY,
                -10,
                Product.create(
                    name = "Product 1",
                    price = BigDecimal(10),
                    quantity = 10,
                    minStock = 5,
                ),
            )

            Movement.create(
                MovementType.ENTRY,
                0,
                Product.create(
                    name = "Product 1",
                    price = BigDecimal(10),
                    quantity = 10,
                    minStock = 5,
                ),
            )
        }
    }


    @Test
    fun `should decrease product quantity when type is out`() {
        val product = Product.create(
            name = "Product 1",
            price = BigDecimal(10),
            quantity = 10,
            minStock = 5,
        )

        Movement.create(
            MovementType.OUT,
            5,
            product,
        )

        assert(product.quantity == 5)
    }

    @Test
    fun `should increase product quantity when type is in`() {
        val product = Product.create(
            name = "Product 1",
            price = BigDecimal(10),
            quantity = 10,
            minStock = 5,
        )

        Movement.create(
            MovementType.ENTRY,
            5,
            product,
        )

        assert(product.quantity == 15)
    }

    @Test
    fun `should throws error when try to decrease product quantity below zero`() {
        val product = Product.create(
            name = "Product 1",
            price = BigDecimal(10),
            quantity = 10,
            minStock = 5,
        )

        assertThrows<BusinessException> {
            Movement.create(
                MovementType.OUT,
                15,
                product,
            )
        }
    }


}