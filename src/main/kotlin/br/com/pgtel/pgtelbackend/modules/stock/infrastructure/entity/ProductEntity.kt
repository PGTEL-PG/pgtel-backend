package br.com.pgtel.pgtelbackend.modules.stock.infrastructure.entity

import br.com.pgtel.pgtelbackend.modules.stock.domain.Product
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
    val unitPrice: BigDecimal,
    @Column(name = "url_picture")
    val picture: String = "no-image.png",
    @Column(name = "qt_stock")
    val quantity: Int,
    @Column(name = "qt_min_stock")
    val minStock: Int = 5,
    @ManyToOne
    @JoinColumn(name = "cd_unit")
    val unit: ProductUnitEntity,
    @Column(name = "dt_created_at")
    val createdAt: Instant,
    @Column(name = "dt_updated_at")
    val updatedAt: Instant? = null,
    @Column(name = "dt_deleted_at")
    val deletedAt: Instant? = null,
) {
    fun toDomain(): Product {
        return Product.create(
            id = id,
            name = name,
            unitPrice = unitPrice,
            quantity = quantity,
            minStock = minStock,
            picture = picture,
            unit = unit.toDomain()
        )
    }

    companion object {
        fun fromDomain(product: Product): ProductEntity {
            return ProductEntity(
                id = product.id,
                name = product.name,
                unitPrice = product.unitPrice,
                picture = product.picture,
                quantity = product.quantity,
                minStock = product.minStock,
                unit = ProductUnitEntity.fromDomain(product.unit),
                createdAt = product.createdAt,
                updatedAt = product.updatedAt,
                deletedAt = product.deletedAt,
            )
        }
    }
}