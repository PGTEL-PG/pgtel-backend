package br.com.pgtel.pgtelbackend.config.controller

import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import br.com.pgtel.pgtelbackend.core.exceptions.Error
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.util.function.Consumer


@RestControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleBusinessException(e: BusinessException): Error {
        return e.error
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException): Error {
        return Error("BAD_REQUEST", "Invalid parameter: ${e.name}")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        MethodArgumentNotValidException::class
    )
    fun handleValidationExceptions(
        ex: MethodArgumentNotValidException
    ): Map<String, String?>? {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage
        })
        return errors
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): Error {
        return Error("BAD_REQUEST", "Invalid JSON")
    }

//    @ExceptionHandler(Exception::class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    fun handleException(e: Exception): Error {
//        return Error("INTERNAL_SERVER_ERROR", e.message ?: "Unexpected error")
//    }

}