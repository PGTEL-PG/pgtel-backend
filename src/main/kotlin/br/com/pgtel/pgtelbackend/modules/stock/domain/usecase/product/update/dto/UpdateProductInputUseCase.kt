package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.update.dto

import java.math.BigDecimal
import java.util.*

data class UpdateProductInputUseCase(
    val id: UUID,
    val name: String,
    val price: BigDecimal,
    val quantity: Int,
    val minStock: Int,
)