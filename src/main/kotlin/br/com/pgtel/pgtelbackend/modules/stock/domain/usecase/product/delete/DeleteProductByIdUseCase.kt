package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.delete

import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.delete.dto.DeleteProductByIdInputUseCase

class DeleteProductByIdUseCase(
    private val productGateway: ProductGateway
) {

    fun execute(input: DeleteProductByIdInputUseCase) {
        productGateway.findById(input.id)?.let {
            it.delete()
            productGateway.save(it)
        }

    }
}
