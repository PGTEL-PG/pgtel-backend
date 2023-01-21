package br.com.pgtel.pgtelbackend.core.exceptions

class BusinessException(val error: Error) : RuntimeException() {
    constructor(code: String, message: String) : this(Error(code, message))
}