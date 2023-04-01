package br.com.pgtel.pgtelbackend.modules.auth

import br.com.pgtel.pgtelbackend.config.security.jwt.JwtUtils
import br.com.pgtel.pgtelbackend.modules.auth.gateway.UserGateway
import br.com.pgtel.pgtelbackend.modules.auth.infrastructure.gateway.DefaultUserGateway
import br.com.pgtel.pgtelbackend.modules.auth.infrastructure.gateway.UserDetailsServiceImpl
import br.com.pgtel.pgtelbackend.modules.auth.infrastructure.gateway.jpa.JPAUserRepository
import br.com.pgtel.pgtelbackend.modules.auth.usecase.authenticateUser.AuthenticateUser
import br.com.pgtel.pgtelbackend.modules.auth.usecase.createUser.CreateUserUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AuthBeanConfig {

    @Bean
    fun authenticateUserUseCase(userGateway: UserGateway): AuthenticateUser {
        return AuthenticateUser(
            userGateway
        )
    }

    @Bean
    fun userGateway(
        userRepository: JPAUserRepository,
        authenticationManager: AuthenticationManager,
        jwtUtils: JwtUtils
    ): UserGateway {
        return DefaultUserGateway(
            userRepository,
            authenticationManager,
            jwtUtils
        )
    }

    @Bean
    fun createUserUseCase(userGateway: UserGateway, passwordEncoder: PasswordEncoder): CreateUserUseCase {
        return CreateUserUseCase(
            userGateway,
            passwordEncoder
        )
    }

    @Bean
    fun userDetailsService(userRepository: JPAUserRepository): UserDetailsService {
        return UserDetailsServiceImpl(userRepository)
    }
}


