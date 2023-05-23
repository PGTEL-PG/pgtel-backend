package br.com.pgtel.pgtelbackend.modules.stock.domain.entities

import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import br.com.pgtel.pgtelbackend.modules.stock.domain.errors.ProductErrors
import java.math.BigDecimal
import java.time.Instant
import java.util.*

class Product private constructor(
    val id: UUID,
    var name: String,
    var price: BigDecimal,
    var picture: String? = null,
    var quantity: Int,
    var minStock: Int = 5,
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant? = null,
    var deletedAt: Instant? = null
) {

    companion object {
        fun create(
            name: String,
            price: BigDecimal,
            quantity: Int,
            minStock: Int?,
        ): Product {
            val product = Product(
                id = UUID.randomUUID(),
                name = name,
                price = price,
                quantity = quantity,
                minStock = minStock ?: 5,
            )

            product.validate()
            return product
        }

        fun create(
            id: UUID,
            name: String,
            picture: String? = null,
            price: BigDecimal,
            quantity: Int,
            minStock: Int? = null,
            createdAt: Instant,
            updatedAt: Instant? = null,
            deletedAt: Instant? = null
        ): Product {
            val product = Product(
                id = id,
                picture = picture,
                name = name,
                price = price,
                quantity = quantity,
                minStock = minStock ?: 5,
                createdAt = createdAt,
                updatedAt = updatedAt,
                deletedAt = deletedAt
            )

            product.validate()
            return product
        }
    }


    private fun validate() {
        if (name.isBlank()) {
            throw BusinessException(ProductErrors.PRODUCT_NAME_INVALID)
        }

        if (price <= BigDecimal.ZERO) {
            throw BusinessException(ProductErrors.PRODUCT_UNIT_PRICE_INVALID)
        }

        if (quantity < 0) {
            throw BusinessException(ProductErrors.PRODUCT_QUANTITY_INVALID)
        }

        if (minStock < 0) {
            throw BusinessException(ProductErrors.PRODUCT_MIN_STOCK_INVALID)
        }
    }

    val isDeleted: Boolean get() = deletedAt != null
    fun delete() {
        if (isDeleted) {
            throw BusinessException(ProductErrors.Product_ALREADY_DELETED)
        }
        this.deletedAt = Instant.now()
        this.updatedAt = Instant.now()
    }

    private fun hasStock(value: Int): Boolean {
        return quantity >= value
    }

    fun decreaseStock(quantity: Int) {
        if (!this.hasStock(quantity)) {
            throw BusinessException(ProductErrors.INSUFFICIENT_STOCK)
        }
        this.quantity -= quantity
        this.updatedAt = Instant.now()
    }

    fun increaseStock(quantity: Int) {
        if (quantity <= 0) {
            throw BusinessException(ProductErrors.PRODUCT_QUANTITY_INVALID)
        }
        this.quantity += quantity
        this.updatedAt = Instant.now()
    }

}
