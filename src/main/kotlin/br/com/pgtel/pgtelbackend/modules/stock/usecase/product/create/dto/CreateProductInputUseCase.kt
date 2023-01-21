package br.com.pgtel.pgtelbackend.modules.stock.usecase.product.create.dto

import java.math.BigDecimal
import java.util.UUID

data class CreateProductInputUseCase(
    val name: String,
    val unitId: UUID,
    val unitPrice: BigDecimal,
    val quantity: Int,
    val minStock: Int?,
)