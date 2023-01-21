package br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.findAll.dto

import java.util.UUID

data class FindAllProductUnitOutputUseCase(
    val id: UUID,
    val name: String
)
