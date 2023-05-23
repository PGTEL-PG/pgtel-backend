package br.com.pgtel.pgtelbackend.modules.stock.infra.entity

import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.Movement
import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity(name = "tb_movement")
class MovementEntity {
    @Id
    @Column(name = "cd_movement")
    var id: UUID? = null

    @Column(name = "qt_products")
    var quantity: Int = 0

    @Column(name = "tp_movement")
    var type: String? = null

    @ManyToOne
    @JoinColumn(name = "cd_product")
    lateinit var product: ProductEntity

    @Column(name = "dt_created_at")
    var createdAt: Instant = Instant.now()

    constructor(id: UUID?, quantity: Int, type: String?, product: ProductEntity, createdAt: Instant) {
        this.id = id
        this.quantity = quantity
        this.type = type
        this.product = product
        this.createdAt = createdAt
    }

    constructor() {

    }


    fun toDomain(): Movement {
        return Movement.create(
            id = id!!,
            type = enumValueOf(type!!.trim()),
            quantity = quantity,
            product = product.toDomain(),
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
                createdAt = movement.createdAt
            )
        }
    }
}
