package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.dashboard.productsStatistic.dto

data class ProductsStatisticOutputUseCase(
    val totalProducts: Int,
    val totalProductsOutOfStock: Int,
    val totalProductsInStockWithHighQuantity: Int,
    val lastMovements: List<MovementDashboard>
)

data class MovementDashboard(
    val id: String,
    val type: String,
    val quantity: Int,
    val productName: String,
    val productPicture: String?
)