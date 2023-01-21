package br.com.pgtel.pgtelbackend.modules.stock.usecase.product.findPageable.dto

data class FindProductsPageableInputUseCase(
    val page: Int,
    val size: Int,
    val name: String?,
)
