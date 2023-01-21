package br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.delete

import br.com.pgtel.pgtelbackend.modules.stock.gateway.ProductUnitGateway
import br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.delete.dto.DeleteUnitByIdInputUseCase

class DeleteUnitByIdUseCase(
    private val gateway: ProductUnitGateway
) {

    fun execute(input: DeleteUnitByIdInputUseCase) {
        gateway.findById(input.id)?.let {
            it.delete()
            gateway.save(it)
        }
    }

}