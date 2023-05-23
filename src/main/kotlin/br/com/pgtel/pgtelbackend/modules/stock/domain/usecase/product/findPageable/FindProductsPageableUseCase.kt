package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.findPageable

import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.findPageable.dto.FindProductsPageableInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.findPageable.dto.FindProductsPageableOutputUseCase
import java.math.BigDecimal

class FindProductsPageableUseCase(
    private val gateway: ProductGateway
) {

    fun execute(input: FindProductsPageableInputUseCase): FindProductsPageableOutputUseCase {
        return gateway.findProductsPageable(page = input.page, size = input.size, name = input.name).map {
            FindProductsPageableOutputUseCase.ProductOutputUseCase(
                id = it.id,
                name = it.name,
                price = it.price.multiply(BigDecimal(100)).intValueExact(),
                quantity = it.quantity,
                minStock = it.minStock,
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