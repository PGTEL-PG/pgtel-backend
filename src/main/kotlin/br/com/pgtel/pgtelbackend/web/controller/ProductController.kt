package br.com.pgtel.pgtelbackend.web.controller

import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.create.CreateProductUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.create.dto.CreateProductInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.create.dto.CreateProductOutputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.delete.DeleteProductByIdUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.delete.dto.DeleteProductByIdInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.findById.FindProductByIdUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.findById.dto.FindProductByIdInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.findById.dto.FindProductByIdOutputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.findPageable.FindProductsPageableUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.findPageable.dto.FindProductsPageableInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.findPageable.dto.FindProductsPageableOutputUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.*

@RestController
@RequestMapping("/products")
class ProductController(
    val createProductUseCase: CreateProductUseCase,
    val findProductByIdUseCase: FindProductByIdUseCase,
    val findProductsPageableUseCase: FindProductsPageableUseCase,
    val deleteProductByIdUseCase: DeleteProductByIdUseCase,
) {

    @PostMapping
    fun create(@RequestBody body: ProductRequest): CreateProductOutputUseCase {
        return createProductUseCase.execute(body.toInputUseCase())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): FindProductByIdOutputUseCase {
        return findProductByIdUseCase.execute(FindProductByIdInputUseCase(id))
    }

    @GetMapping
    fun getProductsPageable(params: ProductPageableRequestParams): FindProductsPageableOutputUseCase {
        return findProductsPageableUseCase.execute(
            FindProductsPageableInputUseCase(
                params.page,
                params.size,
                params.name
            )
        )
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable id: UUID) {
        deleteProductByIdUseCase.execute(DeleteProductByIdInputUseCase(id))
    }
}

data class ProductRequest(
    val name: String,
    val price: BigDecimal,
    val quantity: Int,
    val minStock: Int?,
    val unitId: UUID
) {
    fun toInputUseCase() = CreateProductInputUseCase(
        name = name,
        unitPrice = price,
        quantity = quantity,
        minStock = minStock,
        unitId = unitId
    )
}

data class ProductPageableRequestParams(
    val page: Int = 0,
    val size: Int = 10,
    val name: String? = null
)