package br.com.pgtel.pgtelbackend.modules.stock.usecase.product.findPageable.dto

import java.time.Instant
import java.util.*

data class FindProductsPageableOutputUseCase(
    val totalItems: Int,
    val isLastPage: Boolean,
    val content: List<ProductOutputUseCase>
) {
    data class ProductOutputUseCase(
        val id: UUID,
        val name: String,
        val unitPrice: Int,
        val quantity: Int,
        val minStock: Int?,
        val unitId: UUID,
        val unitName: String,
        val picture: String,
        val createdAt: Instant
    )
}
