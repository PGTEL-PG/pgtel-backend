package br.com.pgtel.pgtelbackend.modules.stock.usecase.product.findPageable

import br.com.pgtel.pgtelbackend.modules.stock.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.findPageable.dto.FindProductsPageableInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.findPageable.dto.FindProductsPageableOutputUseCase
import java.math.BigDecimal

class FindProductsPageableUseCase(
    private val gateway: ProductGateway
) {

    fun execute(input: FindProductsPageableInputUseCase): FindProductsPageableOutputUseCase {
        return gateway.findProductsPageable(page = input.page, size = input.size, name = input.name).map {
            FindProductsPageableOutputUseCase.ProductOutputUseCase(
                id = it.id,
                name = it.name,
                unitPrice = it.unitPrice.multiply(BigDecimal(100)).intValueExact(),
                quantity = it.quantity,
                minStock = it.minStock,
                unitId = it.unit.id,
                unitName = it.unit.name,
                picture = it.picture,
                createdAt = it.createdAt
            )
        }.let {
            FindProductsPageableOutputUseCase(
                totalItems = it.totalElements.toInt(),
                isLastPage = it.isLast,
                content = it.content
            )
        }
    }
}