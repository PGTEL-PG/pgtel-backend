package br.com.pgtel.pgtelbackend.modules.stock.infra.controllers

import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.create.CreateProductUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.create.dto.CreateProductInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.create.dto.CreateProductOutputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.delete.DeleteProductByIdUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.delete.dto.DeleteProductByIdInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.findById.FindProductByIdUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.findById.dto.FindProductByIdInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.findById.dto.FindProductByIdOutputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.findPageable.FindProductsPageableUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.findPageable.dto.FindProductsPageableInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.findPageable.dto.FindProductsPageableOutputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.update.UpdateProductUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.update.dto.UpdateProductInputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.update.dto.UpdateProductOutputUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.uploadImage.UploadProductImageUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.uploadImage.dto.UploadProductImageUseCaseInput
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.uploadImage.dto.UploadProductImageUseCaseOutput
import br.com.pgtel.pgtelbackend.utils.FileUtils
import jakarta.validation.Valid
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.math.BigDecimal
import java.util.*

@RestController
@RequestMapping("/products")

class ProductController(
    val createProductUseCase: CreateProductUseCase,
    val findProductByIdUseCase: FindProductByIdUseCase,
    val findProductsPageableUseCase: FindProductsPageableUseCase,
    val deleteProductByIdUseCase: DeleteProductByIdUseCase,
    val uploadProductImageUseCase: UploadProductImageUseCase,
    val updateProductUseCase: UpdateProductUseCase
) {

    @PostMapping
    fun create(@Valid @RequestBody body: ProductRequest): CreateProductOutputUseCase {
        return createProductUseCase.execute(body.toInputUseCase())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): FindProductByIdOutputUseCase {
        return findProductByIdUseCase.execute(FindProductByIdInputUseCase(id))
    }

    @PostMapping("/{id}/image")
    fun uploadImage(
        @PathVariable id: UUID, @RequestParam("image") multipartFile: MultipartFile
    ): UploadProductImageUseCaseOutput {
        return uploadProductImageUseCase.execute(
            UploadProductImageUseCaseInput(
                id = id, extension = FileUtils.getFileExtension(multipartFile), image = multipartFile.bytes
            )
        )

    }

    @GetMapping
    fun getProductsPageable(params: ProductPageableRequestParams): FindProductsPageableOutputUseCase {
        return findProductsPageableUseCase.execute(
            FindProductsPageableInputUseCase(
                params.page, params.size, params.name
            )
        )
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable id: UUID) {
        deleteProductByIdUseCase.execute(DeleteProductByIdInputUseCase(id))
    }

    @PutMapping("{id}")
    fun updateById(@PathVariable id: UUID, @Valid @RequestBody body: UpdateProductRequest): UpdateProductOutputUseCase {
        return updateProductUseCase.execute(
            UpdateProductInputUseCase(
                id, body.name, body.price, body.quantity, body.minStock
            )
        )
    }

}

data class ProductRequest(

    @field:NotBlank val name: String,

    @field:DecimalMin("0.01") val price: BigDecimal,

    @field:Min(0) val quantity: Int,

    val minStock: Int?,

    ) {
    fun toInputUseCase() = CreateProductInputUseCase(
        name = name,
        price = price,
        quantity = quantity,
        minStock = minStock,
    )
}

data class UpdateProductRequest(

    @field:NotBlank val name: String,

    @field:DecimalMin("0.01") val price: BigDecimal,

    @field:Min(0) val quantity: Int,

    @field:Min(0) val minStock: Int,

    )

data class ProductPageableRequestParams(
    val page: Int = 0, val size: Int = 10, val name: String? = null
)