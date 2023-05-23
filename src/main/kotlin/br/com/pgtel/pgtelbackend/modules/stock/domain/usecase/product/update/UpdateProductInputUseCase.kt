package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.update

import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import br.com.pgtel.pgtelbackend.core.usecase.Usecase
import br.com.pgtel.pgtelbackend.modules.stock.domain.errors.ProductErrors
import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.update.dto.UpdateProductInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.update.dto.UpdateProductOutputUseCase
import java.math.BigDecimal
import java.util.*


class UpdateProductUseCase(
    private val productGateway: ProductGateway
) : Usecase<UpdateProductOutputUseCase, UpdateProductInputUseCase> {

    override fun execute(input: UpdateProductInputUseCase): UpdateProductOutputUseCase {
        val product = productGateway.findById(input.id) ?: throw BusinessException(ProductErrors.PRODUCT_NOT_FOUND)
        product.apply {
            name = input.name
            price = (input.price).divide(BigDecimal(100))
            quantity = input.quantity
            minStock = input.minStock
        }
        productGateway.save(product)
        return UpdateProductOutputUseCase.fromDomain(product)
    }
}
