package br.com.pgtel.pgtelbackend.modules.stock.infrastructure.gateway

import br.com.pgtel.pgtelbackend.modules.stock.domain.Movement
import br.com.pgtel.pgtelbackend.modules.stock.gateway.MovementGateway
import br.com.pgtel.pgtelbackend.modules.stock.infrastructure.entity.MovementEntity
import br.com.pgtel.pgtelbackend.modules.stock.infrastructure.gateway.jpa.JPAMovementRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.util.*

class DefaultMovementGateway(
    private val repository: JPAMovementRepository
) : MovementGateway {
    override fun findMovementsPageable(page: Int, size: Int, sort: String, direction: String): Page<Movement> {
        val pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort))
        return repository.findAll(
            pageable
        ).map { it.toDomain() }
    }

    override fun save(movement: Movement) {
        MovementEntity.fromDomain(movement).let {
            repository.save(it)
        }
    }

    override fun delete(id: UUID) {
        repository.findById(id).ifPresent {
            repository.delete(it)
        }
    }

    override fun findById(id: UUID): Movement? {
        return repository.findById(id).map { it.toDomain() }.orElse(null)
    }
}
