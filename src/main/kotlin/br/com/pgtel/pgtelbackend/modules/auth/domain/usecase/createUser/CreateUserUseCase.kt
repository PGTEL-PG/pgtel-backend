package br.com.pgtel.pgtelbackend.modules.auth.domain.usecase.createUser

import br.com.pgtel.pgtelbackend.modules.auth.domain.entities.User
import br.com.pgtel.pgtelbackend.modules.auth.domain.gateway.UserGateway
import br.com.pgtel.pgtelbackend.modules.auth.domain.usecase.createUser.dto.CreateUserInputUseCase
import br.com.pgtel.pgtelbackend.modules.auth.domain.usecase.createUser.dto.CreateUserOutputUseCase
import org.springframework.security.crypto.password.PasswordEncoder

class CreateUserUseCase(
    private val userGateway: UserGateway,
    private val passwordEncoder: PasswordEncoder
) {

    fun execute(input: CreateUserInputUseCase): CreateUserOutputUseCase {

        val user = User.create(
            name = input.name,
            email = input.email,
            password = passwordEncoder.encode(input.password)
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