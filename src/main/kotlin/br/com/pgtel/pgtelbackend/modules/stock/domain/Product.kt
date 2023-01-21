package br.com.pgtel.pgtelbackend.modules.stock.domain

import br.com.pgtel.pgtelbackend.modules.stock.errors.ProductErrors
import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

class Product private constructor(
    val id: UUID,
    val unit: ProductUnit,
    val name: String,
    val unitPrice: BigDecimal,
    val picture: String = "no-image.png",
    var quantity: Int,
    val minStock: Int = 5,
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant? = null,
    var deletedAt: Instant? = null
) {

    private val isDeleted: Boolean = deletedAt != null

    companion object {
        fun create(
            name: String,
            unitPrice: BigDecimal,
            quantity: Int,
            minStock: Int?,
            unit: ProductUnit
        ): Product {
            val product = Product(
                id = UUID.randomUUID(),
                name = name,
                unitPrice = unitPrice,
                quantity = quantity,
                minStock = minStock ?: 5,
                unit = unit,
            )

            product.validate()
            return product
        }

        fun create(
            id: UUID,
            name: String,
            picture: String,
            unitPrice: BigDecimal,
            quantity: Int,
            minStock: Int?,
            unit: ProductUnit
        ): Product {
            val product = Product(
                id = id,
                picture = picture,
                name = name,
                unitPrice = unitPrice,
                quantity = quantity,
                minStock = minStock ?: 5,
                unit = unit
            )

            product.validate()
            return product
        }
    }


    fun validate() {
        if (name.isBlank()) {
            throw BusinessException(ProductErrors.PRODUCT_NAME_INVALID)
        }

        if (unitPrice <= BigDecimal.ZERO) {
            throw BusinessException(ProductErrors.PRODUCT_UNIT_PRICE_INVALID)
        }

        if (quantity < 0) {
            throw BusinessException(ProductErrors.PRODUCT_QUANTITY_INVALID)
        }

        if (minStock < 0) {
            throw BusinessException(ProductErrors.PRODUCT_MIN_STOCK_INVALID)
        }
    }

    fun delete() {
        if (isDeleted) {
            throw BusinessException(ProductErrors.Product_ALREADY_DELETED)
        }
        this.deletedAt = Instant.now()
        this.updatedAt = Instant.now()
    }

    fun hasStock(value: Int): Boolean {
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
        this.quantity += quantity
        this.updatedAt = Instant.now()
    }

}
