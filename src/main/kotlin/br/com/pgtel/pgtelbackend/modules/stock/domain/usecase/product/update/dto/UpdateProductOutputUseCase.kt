package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.update.dto

import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.Product
import java.math.BigDecimal
import java.time.Instant
import java.util.*

data class UpdateProductOutputUseCase(
    val id: UUID,
    val name: String,
    val price: Int,
    val picture: String?,
    val quantity: Int,
    val minStock: Int,
    val createdAt: Instant,
) {
    companion object {
        fun fromDomain(product: Product) = UpdateProductOutputUseCase(
            id = product.id,
            name = product.name,
            price = product.price.multiply(BigDecimal(100)).intValueExact(),
            picture = product.picture,
            quantity = product.quantity,
            minStock = product.minStock,
            createdAt = product.createdAt,
        )
    }

}