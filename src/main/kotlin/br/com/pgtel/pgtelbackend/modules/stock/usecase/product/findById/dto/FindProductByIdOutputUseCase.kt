package br.com.pgtel.pgtelbackend.modules.stock.usecase.product.findById.dto

import java.time.Instant
import java.util.*

data class FindProductByIdOutputUseCase(
    val id: UUID,
    val name: String,
    val unitName: String,
    val unitPrice: Int,
    val picture: String,
    val quantity: Int,
    val minStock: Int,
    val createAt: Instant,
)