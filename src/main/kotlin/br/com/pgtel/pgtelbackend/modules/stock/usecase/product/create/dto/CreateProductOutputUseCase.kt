package br.com.pgtel.pgtelbackend.modules.stock.usecase.product.create.dto

import br.com.pgtel.pgtelbackend.modules.stock.domain.Product
import java.math.BigDecimal
import java.time.Instant
import java.util.*

data class CreateProductOutputUseCase(
    val id: UUID,
    val name: String,
    val unitName: String,
    val unitPrice: Int,
    val picture: String,
    val quantity: Int,
    val minStock: Int,
    val createAt: Instant,
) {
    companion object {
        fun fromDomain(product: Product) = CreateProductOutputUseCase(
            id = product.id,
            name = product.name,
            unitPrice = product.unitPrice.multiply(BigDecimal(100)).intValueExact(),
            picture = product.picture,
            quantity = product.quantity,
            minStock = product.minStock,
            createAt = product.createdAt,
            unitName = product.unit.name,
        )
    }

}