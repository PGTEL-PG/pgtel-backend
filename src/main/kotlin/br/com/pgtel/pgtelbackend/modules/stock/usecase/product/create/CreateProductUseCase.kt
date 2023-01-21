package br.com.pgtel.pgtelbackend.modules.stock.usecase.product.create

import br.com.pgtel.pgtelbackend.modules.stock.domain.Product
import br.com.pgtel.pgtelbackend.modules.stock.errors.ProductErrors
import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import br.com.pgtel.pgtelbackend.modules.stock.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.gateway.ProductUnitGateway
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.create.dto.CreateProductInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.create.dto.CreateProductOutputUseCase

class CreateProductUseCase(
    private val productGateway: ProductGateway,
    private val productUnitGateway: ProductUnitGateway
) {
    fun execute(input: CreateProductInputUseCase): CreateProductOutputUseCase {
        val productUnit = productUnitGateway.findById(input.unitId) ?: throw BusinessException(ProductErrors.UNIT_NOT_FOUND)
        val product = Product.create(input.name, input.unitPrice, input.quantity, input.minStock, unit = productUnit)
        productGateway.save(product)
        return CreateProductOutputUseCase.fromDomain(product)
    }
}