package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.create

import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.Product
import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.create.dto.CreateProductInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.create.dto.CreateProductOutputUseCase

class CreateProductUseCase(
    private val productGateway: ProductGateway,
) {
    fun execute(input: CreateProductInputUseCase): CreateProductOutputUseCase {
        val product = Product.create(input.name, input.price, input.quantity, input.minStock)
        productGateway.save(product)
        return CreateProductOutputUseCase.fromDomain(product)
    }
}