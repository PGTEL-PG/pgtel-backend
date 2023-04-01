package br.com.pgtel.pgtelbackend.web.controller

import br.com.pgtel.pgtelbackend.modules.auth.usecase.createUser.CreateUserUseCase
import br.com.pgtel.pgtelbackend.modules.auth.usecase.createUser.dto.CreateUserInputUseCase
import br.com.pgtel.pgtelbackend.modules.auth.usecase.createUser.dto.CreateUserOutputUseCase
import br.com.pgtel.pgtelbackend.web.config.security.jwt.JwtUtils
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils,
    private val createUserUseCase: CreateUserUseCase
) {

    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): ResponseEntity<*> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.username,
                loginRequest.password
            )
        )
        val userDetails = authentication.principal as UserDetails
        val token = jwtUtils.generateJwtTokenFromUsername(userDetails.username)
        return ResponseEntity.ok(LoginResponse("Nome",loginRequest.username, "http://avatar.com.br",token))

    }


    @PostMapping("/register")
    fun register(@RequestBody user: CreateUserInputUseCase): CreateUserOutputUseCase {
        return createUserUseCase.execute(user)
    }
}

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val name: String = "Nome",
    val username: String,
    val avatar: String? = null,
    val token: String
)