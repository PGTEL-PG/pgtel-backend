package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.findById.dto

import java.time.Instant
import java.util.*

data class FindProductByIdOutputUseCase(
    val id: UUID,
    val name: String,
    val price: Int,
    val picture: String?,
    val quantity: Int,
    val minStock: Int,
    val createdAt: Instant,
)