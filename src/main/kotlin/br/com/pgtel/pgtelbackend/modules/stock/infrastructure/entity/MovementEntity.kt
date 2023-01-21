package br.com.pgtel.pgtelbackend.modules.stock.infrastructure.entity

import br.com.pgtel.pgtelbackend.modules.stock.domain.Movement
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Entity(name = "tb_movement")
class MovementEntity(
    @Id
    @Column(name = "cd_movement")
    val id: UUID,
    @Column(name = "qt_products")
    val quantity: Int,
    @Column(name = "tp_movement")
    val type: String,
    @ManyToOne
    @JoinColumn(name = "cd_product")
    val product: ProductEntity,
    @Column(name = "vl_unit_price", nullable = true)
    val unitPrice: BigDecimal?,
    @Column(name = "dt_created_at")
    val createdAt: Instant
) {
    fun toDomain(): Movement {
        println(type.length)
        return Movement.create(
            id = id,
            type = enumValueOf(type.trim()),
            quantity = quantity,
            product = product.toDomain(),
            unitPrice = unitPrice,
            createdAt = createdAt
        )
    }

    companion object {
        fun fromDomain(movement: Movement): MovementEntity {
            return MovementEntity(
                id = movement.id,
                type = movement.type.name,
                quantity = movement.quantity,
                product = ProductEntity.fromDomain(movement.product),
                unitPrice = movement.unitPrice,
                createdAt = movement.createdAt
            )
        }
    }
}
