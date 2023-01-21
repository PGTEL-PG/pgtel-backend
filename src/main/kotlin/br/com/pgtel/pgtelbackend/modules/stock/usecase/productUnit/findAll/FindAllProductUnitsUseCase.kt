package br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.findAll

import br.com.pgtel.pgtelbackend.modules.stock.gateway.ProductUnitGateway
import br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.findAll.dto.FindAllProductUnitOutputUseCase

class FindAllProductUnitsUseCase(
    private val gateway: ProductUnitGateway
) {
    fun execute(): Set<FindAllProductUnitOutputUseCase> {
        return gateway.findAll().map { unit ->
            FindAllProductUnitOutputUseCase(
                unit.id,
                unit.name
            )
        }.toSet()
    }
}