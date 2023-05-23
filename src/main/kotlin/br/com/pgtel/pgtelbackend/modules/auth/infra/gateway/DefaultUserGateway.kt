package br.com.pgtel.pgtelbackend.modules.auth.infra.gateway

import br.com.pgtel.pgtelbackend.config.security.jwt.JwtUtils
import br.com.pgtel.pgtelbackend.modules.auth.domain.entities.User
import br.com.pgtel.pgtelbackend.modules.auth.domain.gateway.UserGateway
import br.com.pgtel.pgtelbackend.modules.auth.infra.entity.UserEntity
import br.com.pgtel.pgtelbackend.modules.auth.infra.gateway.jpa.JPAUserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class DefaultUserGateway(
    private val repository: JPAUserRepository,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils,
) : UserGateway {

    override fun save(user: User) {
        UserEntity.fromDomain(user).let {
            repository.save(it)
        }
    }

    override fun getBy(username: String, password: String): User {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                username,
                password
            )
        )

        val userDetails = authentication.principal as UserEntity
        val userDomain = userDetails.toDomain()
        val token = jwtUtils.generateJwtTokenFromUsername(userDetails.username)
        userDomain.token(token)
        return userDomain

    }

}