package br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.create

import br.com.pgtel.pgtelbackend.modules.stock.domain.ProductUnit
import br.com.pgtel.pgtelbackend.modules.stock.gateway.ProductUnitGateway
import br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.create.dto.CreateProductUnitInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.create.dto.CreateProductUnitOutputUseCase

class CreateProductUnitUseCase(
    private val gateway: ProductUnitGateway
) {
    fun execute(input: CreateProductUnitInputUseCase): CreateProductUnitOutputUseCase {
        val unit = ProductUnit.create(input.name)
        gateway.save(unit)
        return CreateProductUnitOutputUseCase(
            unit.id,
            unit.name
        )
    }
}