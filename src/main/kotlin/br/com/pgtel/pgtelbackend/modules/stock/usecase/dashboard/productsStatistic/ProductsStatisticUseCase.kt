package br.com.pgtel.pgtelbackend.modules.stock.usecase.dashboard.productsStatistic

import br.com.pgtel.pgtelbackend.modules.stock.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.usecase.dashboard.productsStatistic.dto.ProductsStatisticOutputUseCase

class ProductsStatisticUseCase(
    private val gateway: ProductGateway
) {
    fun execute(): ProductsStatisticOutputUseCase {
        val totalProducts = gateway.countAll()
        val totalProductsOutOfStock = gateway.countAllOutOfStock()
        val totalProductsInStockWithHighQuantity = gateway.countAllInStockWithHighQuantity()

        return ProductsStatisticOutputUseCase(
            totalProducts,
            totalProductsOutOfStock,
            totalProductsInStockWithHighQuantity
        )
    }
}