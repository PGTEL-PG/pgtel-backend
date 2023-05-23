package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.uploadImage

import br.com.pgtel.pgtelbackend.core.usecase.Usecase
import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.FileUploaderGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.uploadImage.dto.UploadProductImageUseCaseInput
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.uploadImage.dto.UploadProductImageUseCaseOutput
import java.util.UUID

class UploadProductImageUseCase(
    private val productRepository: ProductGateway,
    private val fileUploaderGateway: FileUploaderGateway
) : Usecase<UploadProductImageUseCaseOutput, UploadProductImageUseCaseInput> {

    companion object {
        const val IMAGE_PATH = "products/"
    }


    override fun execute(input: UploadProductImageUseCaseInput): UploadProductImageUseCaseOutput {
        productRepository.findById(input.id)?.let {
            val fileName = UUID.randomUUID().toString().plus("." + input.extension)
            val fullFilePath = IMAGE_PATH.plus(input.id).plus("/").plus(fileName)
            val imageUrl = fileUploaderGateway.saveImage(
                input.image, fullFilePath
            )
            it.picture = imageUrl
            productRepository.save(it)
            return UploadProductImageUseCaseOutput.fromDomain(it)
        }
        throw Exception("Product not found")
    }


}