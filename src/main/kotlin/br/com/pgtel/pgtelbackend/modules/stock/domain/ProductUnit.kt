package br.com.pgtel.pgtelbackend.modules.stock.domain

import br.com.pgtel.pgtelbackend.modules.stock.errors.ProductUnitErrors
import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import java.time.Instant
import java.util.*

class ProductUnit private constructor(
    val id: UUID,
    val name: String,
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant? = null,
    var deletedAt: Instant? = null,
) {

    private val isDeleted: Boolean = deletedAt != null

    companion object {
        fun create(name: String): ProductUnit {
            val productUnit = ProductUnit(
                id = UUID.randomUUID(),
                name = name
            )

            productUnit.validate()
            return productUnit
        }

        fun create(id: UUID, name: String): ProductUnit {
            val productUnit = ProductUnit(
                id = id,
                name = name
            )

            productUnit.validate()
            return productUnit
        }
    }

    private fun validate() {
        if (name.isBlank()) {
            throw BusinessException(ProductUnitErrors.PRODUCT_UNIT_NAME_INVALID)
        }
    }


    fun delete() {
        if (isDeleted) {
            throw BusinessException(ProductUnitErrors.PRODUCT_UNIT_ALREADY_DELETED)
        }
        deletedAt = Instant.now()
        updatedAt = Instant.now()

    }

}