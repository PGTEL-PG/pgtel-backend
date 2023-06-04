package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.dashboard.productsStatistic

import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.MovementGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.dashboard.productsStatistic.dto.MovementDashboard
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.dashboard.productsStatistic.dto.ProductsStatisticOutputUseCase

class ProductsStatisticUseCase(
    private val gateway: ProductGateway,
    private val movementGateway: MovementGateway
) {
    fun execute(): ProductsStatisticOutputUseCase {
        val totalProducts = gateway.countAll()
        val totalProductsOutOfStock = gateway.countAllOutOfStock()
        val totalProductsInStockWithHighQuantity = gateway.countAllInStockWithHighQuantity()
        val lastMovements = movementGateway.findMovementsPageable(0, 5, "createdAt", "DESC")

        return ProductsStatisticOutputUseCase(
            totalProducts,
            totalProductsOutOfStock,
            totalProductsInStockWithHighQuantity,
            lastMovements.content.map {
                MovementDashboard(
                    it.id.toString(),
                    it.type.toString(),
                    it.quantity,
                    it.product.name,
                    it.product.picture
                )
            }
        )
    }
}