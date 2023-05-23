package br.com.pgtel.pgtelbackend.modules.auth.domain.gateway

import br.com.pgtel.pgtelbackend.modules.auth.domain.entities.User

interface UserGateway {
    fun save(user: User)
    fun getBy(username: String, password: String): User
}