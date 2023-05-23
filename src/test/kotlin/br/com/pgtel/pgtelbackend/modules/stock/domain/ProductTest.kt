package br.com.pgtel.pgtelbackend.modules.stock.domain

import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.Product
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.time.Instant
import java.util.*


class ProductTest {


    @Test
    fun `should create a product`() {


        assertDoesNotThrow {
            Product.create(
                name = "Product 1",
                price = BigDecimal(10),
                quantity = 10,
                minStock = 5,
            )
        }
    }

    @Test
    fun `should not create a product with invalid name`() {
        assertThrows<BusinessException> {
            Product.create(
                name = "",
                price = BigDecimal(10),
                quantity = 10,
                minStock = 5,
            )
        }
    }

    @Test
    fun `should not create a product with invalid unit price`() {
        assertThrows<BusinessException> {
            Product.create(
                name = "Product 1",
                price = BigDecimal(-10),
                quantity = 10,
                minStock = 5,
            )
        }
    }

    @Test
    fun `should not create a product with invalid quantity`() {

        assertThrows<BusinessException> {
            Product.create(
                name = "Product 1",
                price = BigDecimal(10),
                quantity = -10,
                minStock = 5,
            )
        }
    }

    @Test
    fun `should not create a product with invalid min stock`() {

        assertThrows<BusinessException> {
            Product.create(
                name = "Product 1",
                price = BigDecimal(10),
                quantity = 10,
                minStock = -5,
            )
        }
    }

    @Test
    fun `should create a product with id`() {

        assertDoesNotThrow {
            val id = UUID.randomUUID()
            val product = Product.create(
                id = id,
                name = "Product 1",
                price = BigDecimal(10),
                quantity = 10,
                minStock = 5,
                createdAt = Instant.now(),
            )
            assert(product.id == id)
        }
    }

    @Test
    fun `should create a product with picture`() {

        assertDoesNotThrow {
            val product = Product.create(
                id = UUID.randomUUID(),
                name = "Product 1",
                picture = "picture",
                price = BigDecimal(10),
                quantity = 10,
                minStock = 5,
                createdAt = Instant.now(),
            )
            assert(product.picture == "picture")
        }
    }

    @Test
    fun `should delete a product`() {


        val product = Product.create(
            id = UUID.randomUUID(),
            name = "Product 1",
            picture = "picture",
            price = BigDecimal(10),
            quantity = 10,
            minStock = 5,
            createdAt = Instant.now(),
        )
        product.delete()
        assert(product.isDeleted)
    }


    @Test
    fun `should decrease stock`() {
        val product = Product.create(
            id = UUID.randomUUID(),
            name = "Product 1",
            picture = "picture",
            price = BigDecimal(10),
            quantity = 10,
            minStock = 5,
            createdAt = Instant.now(),
        )

        product.decreaseStock(5)

        assert(product.quantity == 5)
        assert(product.updatedAt != null)

        assertThrows<BusinessException> {
            product.decreaseStock(10)
        }

    }

    @Test
    fun `should increase stock`() {

        val product = Product.create(
            id = UUID.randomUUID(),
            name = "Product 1",
            picture = "picture",
            price = BigDecimal(10),
            quantity = 10,
            minStock = 5,
            createdAt = Instant.now(),
        )

        product.increaseStock(5)

        assert(product.quantity == 15)

        assertThrows<BusinessException> {
            product.increaseStock(-10)
        }
    }


}
