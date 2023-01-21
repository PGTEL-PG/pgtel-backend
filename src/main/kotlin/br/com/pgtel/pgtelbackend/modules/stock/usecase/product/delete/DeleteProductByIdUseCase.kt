package br.com.pgtel.pgtelbackend.modules.stock.usecase.product.delete

import br.com.pgtel.pgtelbackend.modules.stock.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.delete.dto.DeleteProductByIdInputUseCase

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
