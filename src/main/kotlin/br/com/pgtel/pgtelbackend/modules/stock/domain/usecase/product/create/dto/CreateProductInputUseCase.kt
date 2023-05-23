package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.create.dto

import java.math.BigDecimal

data class CreateProductInputUseCase(
    val name: String, // minimo 3 caracteres
    val price: BigDecimal, // acima de 0
    val quantity: Int,
    val minStock: Int?,
)