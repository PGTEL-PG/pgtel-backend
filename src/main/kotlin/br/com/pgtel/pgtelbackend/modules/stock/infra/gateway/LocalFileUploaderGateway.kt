package br.com.pgtel.pgtelbackend.modules.stock.infra.gateway

import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.FileUploaderGateway
import java.nio.file.Files
import java.nio.file.Paths

class LocalFileUploaderGateway : FileUploaderGateway {

    companion object {
        const val IMAGE_FOLDER = "/Users/paulo.costa/Documents/pictures"
    }

    override fun saveImage(image: ByteArray, fullFilePath: String): String {
        val path = Paths.get(IMAGE_FOLDER, fullFilePath)
        Files.createDirectories(path.parent)
        Files.write(path, image)
        return "http://localhost:8080/static/$fullFilePath"
    }
}