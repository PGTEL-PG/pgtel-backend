package br.com.pgtel.pgtelbackend.modules.stock.infra.entity

import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.Product
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Entity(name = "tb_product")
class ProductEntity(
    @Id
    @Column(name = "cd_product", length = 255)
    val id: UUID,
    @Column(name = "nm_product", length = 255)
    val name: String,
    @Column(name = "vl_unit")
    val price: BigDecimal,
    @Column(name = "url_picture")
    val picture: String? = null,
    @Column(name = "qt_stock")
    val quantity: Int,
    @Column(name = "qt_min_stock")
    val minStock: Int = 5,
    @Column(name = "dt_created_at")
    val createdAt: Instant,
    @Column(name = "dt_updated_at")
    val updatedAt: Instant? = null,
    @Column(name = "dt_deleted_at")
    val deletedAt: Instant? = null,
) {
    constructor() : this(
        id = UUID.randomUUID(),
        name = "",
        price = BigDecimal.ZERO,
        quantity = 0,
        createdAt = Instant.now(),
    ) {

    }

    fun toDomain(): Product {
        return Product.create(
            id = id,
            name = name,
            price = price,
            quantity = quantity,
            minStock = minStock,
            picture = picture,
            createdAt = createdAt,
            updatedAt = updatedAt,
            deletedAt = deletedAt,
        )
    }

    companion object {
        fun fromDomain(product: Product): ProductEntity {
            return ProductEntity(
                id = product.id,
                name = product.name,
                price = product.price,
                picture = product.picture,
                quantity = product.quantity,
                minStock = product.minStock,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt,
                deletedAt = product.deletedAt,
            )
        }
    }
}