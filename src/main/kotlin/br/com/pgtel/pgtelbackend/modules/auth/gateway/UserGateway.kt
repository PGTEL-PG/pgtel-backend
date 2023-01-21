package br.com.pgtel.pgtelbackend.modules.auth.gateway

import br.com.pgtel.pgtelbackend.modules.auth.domain.User

interface UserGateway {
    fun save(user: User)
}