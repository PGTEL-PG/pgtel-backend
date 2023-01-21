package br.com.pgtel.pgtelbackend.modules.stock.infrastructure.gateway.jpa

import br.com.pgtel.pgtelbackend.modules.stock.infrastructure.entity.ProductUnitEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface JPAProductUnitRepository : JpaRepository<ProductUnitEntity, UUID> {
    fun findByIdAndDeletedAtIsNull(id: UUID): Optional<ProductUnitEntity>
    fun findAllByDeletedAtIsNull(): List<ProductUnitEntity>
    fun existsByNameIgnoreCase(name: String): Boolean
}
