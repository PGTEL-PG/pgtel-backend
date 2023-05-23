package br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.uploadImage.dto

import java.util.*

data class UploadProductImageUseCaseInput(
    val id: UUID,
    val extension: String,
    val image: ByteArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UploadProductImageUseCaseInput

        if (id != other.id) return false
        if (extension != other.extension) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + extension.hashCode()
        result = 31 * result + image.contentHashCode()
        return result
    }
}