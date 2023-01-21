package br.com.pgtel.pgtelbackend.modules.stock.usecase.product.delete.dto

import java.util.UUID

data class DeleteProductByIdInputUseCase(
    val id: UUID
)
