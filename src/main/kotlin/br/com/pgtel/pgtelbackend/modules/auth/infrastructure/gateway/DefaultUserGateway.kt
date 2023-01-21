package br.com.pgtel.pgtelbackend.modules.auth.infrastructure.gateway

import br.com.pgtel.pgtelbackend.modules.auth.domain.User
import br.com.pgtel.pgtelbackend.modules.auth.gateway.UserGateway
import br.com.pgtel.pgtelbackend.modules.auth.infrastructure.entity.UserEntity
import br.com.pgtel.pgtelbackend.modules.auth.infrastructure.gateway.jpa.JPAUserRepository

class DefaultUserGateway(
    private val repository: JPAUserRepository
) : UserGateway {

    override fun save(user: User) {
        UserEntity.fromDomain(user).let {
            repository.save(it)
        }
    }

}