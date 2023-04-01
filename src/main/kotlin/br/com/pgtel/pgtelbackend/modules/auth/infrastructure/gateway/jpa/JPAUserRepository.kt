package br.com.pgtel.pgtelbackend.modules.auth.infrastructure.gateway.jpa

import br.com.pgtel.pgtelbackend.modules.auth.infrastructure.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface JPAUserRepository : JpaRepository<UserEntity, UUID> {
    fun findByEmail(email: String): UserEntity?
}