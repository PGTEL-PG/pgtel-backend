package br.com.pgtel.pgtelbackend.modules.stock.gateway

import br.com.pgtel.pgtelbackend.modules.stock.domain.Movement
import org.springframework.data.domain.Page
import java.util.*

interface MovementGateway {
    fun findMovementsPageable(page: Int, size: Int, sort: String, direction: String): Page<Movement>
    fun save(movement: Movement)
    fun delete(id: UUID)
    fun findById(id: UUID): Movement?
}
