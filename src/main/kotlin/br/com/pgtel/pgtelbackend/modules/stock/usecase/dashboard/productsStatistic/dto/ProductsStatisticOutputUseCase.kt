package br.com.pgtel.pgtelbackend.modules.stock.usecase.dashboard.productsStatistic.dto

data class ProductsStatisticOutputUseCase(
    val totalProducts: Int,
    val totalProductsOutOfStock: Int,
    val totalProductsInStockWithHighQuantity: Int,
)