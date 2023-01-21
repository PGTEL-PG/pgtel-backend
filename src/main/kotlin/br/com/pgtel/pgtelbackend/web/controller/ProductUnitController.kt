package br.com.pgtel.pgtelbackend.web.controller

import br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.create.CreateProductUnitUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.create.dto.CreateProductUnitInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.delete.DeleteUnitByIdUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.delete.dto.DeleteUnitByIdInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.findAll.FindAllProductUnitsUseCase
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/product-units")
class ProductUnitController(
    private val findAllProductUnitsUseCase: FindAllProductUnitsUseCase,
    private val createProductUnitUseCase: CreateProductUnitUseCase,
    private val deleteUnitByIdUseCase: DeleteUnitByIdUseCase
) {

    @GetMapping
    fun getAll() = findAllProductUnitsUseCase.execute()

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable id: UUID) {
        deleteUnitByIdUseCase.execute(DeleteUnitByIdInputUseCase(id))
    }

    @PostMapping
    fun create(@RequestBody body: ProductUnitRequest) {
        createProductUnitUseCase.execute(body.toInputUseCase())
    }

}

data class ProductUnitRequest(
    val name: String,
) {
    fun toInputUseCase(): CreateProductUnitInputUseCase {
        return CreateProductUnitInputUseCase(
            name = name
        )
    }
}