package br.com.pgtel.pgtelbackend.modules.stock.infrastructure.entity

import br.com.pgtel.pgtelbackend.modules.stock.domain.ProductUnit
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant
import java.util.*

@Entity(name = "tb_unit")
class ProductUnitEntity(
    @Id
    @Column(name = "cd_unit")
    val id: UUID,
    @Column(name = "nm_unit", unique = true)
    val name: String,
    @Column(name = "dt_created_at")
    val createdAt: Instant,
    @Column(name = "dt_updated_at")
    val updatedAt: Instant? = null,
    @Column(name = "dt_deleted_at")
    val deletedAt: Instant? = null,
) {


    companion object {
        fun fromDomain(productUnit: ProductUnit): ProductUnitEntity {
            return ProductUnitEntity(
                id = productUnit.id,
                name = productUnit.name,
                createdAt = productUnit.createdAt,
                updatedAt = productUnit.updatedAt,
                deletedAt = productUnit.deletedAt
            )
        }


    }

    fun toDomain(): ProductUnit {
        return ProductUnit.create(
            id = id,
            name = name
        )
    }
}