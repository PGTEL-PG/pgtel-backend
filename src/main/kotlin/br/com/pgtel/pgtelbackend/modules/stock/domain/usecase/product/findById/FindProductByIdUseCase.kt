package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.findById

import br.com.pgtel.pgtelbackend.modules.stock.domain.errors.ProductErrors
import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.findById.dto.FindProductByIdInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.findById.dto.FindProductByIdOutputUseCase
import java.math.BigDecimal

class FindProductByIdUseCase(
    private val gateway: ProductGateway
) {

    fun execute(input: FindProductByIdInputUseCase): FindProductByIdOutputUseCase {
        gateway.findById(input.id).let {
            if (it == null) throw BusinessException(ProductErrors.PRODUCT_NOT_FOUND)
            return FindProductByIdOutputUseCase(
                id = it.id,
                name = it.name,
                price = it.price.multiply(BigDecimal(100)).intValueExact(),
                picture = it.picture,
                quantity = it.quantity,
                minStock = it.minStock,
                createdAt = it.createdAt,
            )
        }
    }
}