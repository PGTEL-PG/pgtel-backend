package br.com.pgtel.pgtelbackend.modules.auth.usecase.createUser

import br.com.pgtel.pgtelbackend.modules.auth.domain.User
import br.com.pgtel.pgtelbackend.modules.auth.gateway.UserGateway
import br.com.pgtel.pgtelbackend.modules.auth.usecase.createUser.dto.CreateUserInputUseCase
import br.com.pgtel.pgtelbackend.modules.auth.usecase.createUser.dto.CreateUserOutputUseCase

class CreateUserUseCase(
    private val userGateway: UserGateway
) {

    fun execute(input: CreateUserInputUseCase): CreateUserOutputUseCase {

        val user = User.create(
            name = input.name,
            email = input.email,
            password = input.password
        )
        userGateway.save(user)
        return CreateUserOutputUseCase(
            id = user.id,
            name = user.name,
            email = user.email,
            createdAt = user.createdAt
        )
    }
}