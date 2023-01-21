package br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.create.dto

import java.util.UUID

data class CreateProductUnitOutputUseCase(
    val id: UUID,
    val name: String,
)
