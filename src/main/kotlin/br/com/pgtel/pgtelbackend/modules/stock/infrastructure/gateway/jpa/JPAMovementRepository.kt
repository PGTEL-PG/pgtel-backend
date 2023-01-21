package br.com.pgtel.pgtelbackend.modules.stock.infrastructure.gateway.jpa

import br.com.pgtel.pgtelbackend.modules.stock.infrastructure.entity.MovementEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface JPAMovementRepository : JpaRepository<MovementEntity, UUID> {
}
