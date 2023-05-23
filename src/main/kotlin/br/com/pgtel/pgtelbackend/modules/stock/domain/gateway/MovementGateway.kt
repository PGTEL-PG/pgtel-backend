package br.com.pgtel.pgtelbackend.modules.stock.domain.gateway

import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.Movement
import org.springframework.data.domain.Page
import java.util.*

interface MovementGateway {
    fun findMovementsPageable(page: Int, size: Int, sort: String, direction: String): Page<Movement>
    fun save(movement: Movement)
    fun delete(id: UUID)
    fun findById(id: UUID): Movement?
}
