package br.com.pgtel.pgtelbackend.utils

import org.springframework.web.multipart.MultipartFile

abstract class FileUtils {

    companion object {
        fun getFileExtension(multipartFile: MultipartFile): String {
            val fileName = multipartFile.originalFilename
            if (fileName!!.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                return fileName.substring(fileName.lastIndexOf(".") + 1)
            }
            return ""
        }
    }
}