package br.com.pgtel.pgtelbackend.core.usecase

interface Usecase<Output, Input> {

    fun execute(input: Input): Output
}