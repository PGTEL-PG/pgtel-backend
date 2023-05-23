package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.dashboard.productsStatistic

import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.ProductGateway

class ProductsStatisticUseCase(
    private val gateway: ProductGateway
) {
    fun execute(): br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.dashboard.productsStatistic.dto.ProductsStatisticOutputUseCase {
        val totalProducts = gateway.countAll()
        val totalProductsOutOfStock = gateway.countAllOutOfStock()
        val totalProductsInStockWithHighQuantity = gateway.countAllInStockWithHighQuantity()

        return br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.dashboard.productsStatistic.dto.ProductsStatisticOutputUseCase(
            totalProducts,
            totalProductsOutOfStock,
            totalProductsInStockWithHighQuantity
        )
    }
}