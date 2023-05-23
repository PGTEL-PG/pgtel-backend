package br.com.pgtel.pgtelbackend.modules.stock.domain.gateway

interface FileUploaderGateway {

    fun saveImage(image: ByteArray, fullFilePath: String): String
}
