package br.com.pgtel.pgtelbackend.modules.auth.infra.gateway

import br.com.pgtel.pgtelbackend.modules.auth.infra.gateway.jpa.JPAUserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class UserDetailsServiceImpl(
    private val jpaUserRepository: JPAUserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        return jpaUserRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("User not found with email: $email")
    }
}