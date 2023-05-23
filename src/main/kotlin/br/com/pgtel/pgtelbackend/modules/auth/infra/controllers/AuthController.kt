package br.com.pgtel.pgtelbackend.modules.auth.infra.controllers

import br.com.pgtel.pgtelbackend.modules.auth.infra.controllers.dto.AuthenticationRequest
import br.com.pgtel.pgtelbackend.modules.auth.domain.usecase.authenticateUser.AuthenticateUser
import br.com.pgtel.pgtelbackend.modules.auth.domain.usecase.authenticateUser.AuthenticateUserInputUseCase
import br.com.pgtel.pgtelbackend.modules.auth.domain.usecase.authenticateUser.AuthenticateUserOutputUseCase
import br.com.pgtel.pgtelbackend.modules.auth.domain.usecase.createUser.CreateUserUseCase
import br.com.pgtel.pgtelbackend.modules.auth.domain.usecase.createUser.dto.CreateUserInputUseCase
import br.com.pgtel.pgtelbackend.modules.auth.domain.usecase.createUser.dto.CreateUserOutputUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val useCase: AuthenticateUser,
    private val createUserUseCase: CreateUserUseCase
) {

    @PostMapping("/login")
    fun authenticateUser(@RequestBody request: AuthenticationRequest): AuthenticateUserOutputUseCase {
        val input = AuthenticateUserInputUseCase(
            username = request.username,
            password = request.password
        )
        return useCase.execute(input)
    }


    @PostMapping("/register")
    fun register(@RequestBody user: CreateUserInputUseCase): CreateUserOutputUseCase {
        return createUserUseCase.execute(user)
    }
}