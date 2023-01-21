package br.com.pgtel.pgtelbackend.web.controller

import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import br.com.pgtel.pgtelbackend.core.exceptions.Error
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleBusinessException(e: BusinessException): Error {
        return e.error
    }
}