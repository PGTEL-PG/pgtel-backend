package br.com.pgtel.pgtelbackend.modules.auth.usecase.authenticateUser

import br.com.pgtel.pgtelbackend.core.usecase.Usecase
import br.com.pgtel.pgtelbackend.modules.auth.domain.User
import br.com.pgtel.pgtelbackend.modules.auth.gateway.UserGateway

class AuthenticateUser(
    private val userGateway: UserGateway
) : Usecase<AuthenticateUserOutputUseCase, AuthenticateUserInputUseCase> {


    override fun execute(input: AuthenticateUserInputUseCase): AuthenticateUserOutputUseCase {
        try {
            val user: User = userGateway.getBy(input.username, input.password)
            return AuthenticateUserOutputUseCase(
                name = user.name,
                username = user.email,
                avatar = user.avatar,
                token = user.token!!
            )
        } catch (e: Exception) {
            //TODO: Tratar exceção
            throw e
        }
    }
}

data class AuthenticateUserInputUseCase(
    val username: String,
    val password: String
)

data class AuthenticateUserOutputUseCase(
    val name: String,
    val username: String,
    val avatar: String,
    val token: String,
)