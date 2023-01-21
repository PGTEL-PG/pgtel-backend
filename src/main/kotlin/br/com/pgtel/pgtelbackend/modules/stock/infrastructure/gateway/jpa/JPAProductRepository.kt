package br.com.pgtel.pgtelbackend.modules.stock.infrastructure.gateway.jpa

import br.com.pgtel.pgtelbackend.modules.stock.infrastructure.entity.ProductEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface JPAProductRepository : JpaRepository<ProductEntity, UUID> {

    fun findByNameContainingIgnoreCaseAndDeletedAtIsNull(name: String, pageable: PageRequest): Page<ProductEntity>
    fun findAllByDeletedAtIsNull(pageable: PageRequest): Page<ProductEntity>
    fun findByIdAndDeletedAtIsNull(id: UUID): Optional<ProductEntity>
    fun countByDeletedAtIsNull(): Int
    @Query("SELECT COUNT(p) FROM tb_product p WHERE p.deletedAt IS NULL AND p.quantity < p.minStock")
    fun countQuantityIsLessThanMinStock(): Int
    @Query("SELECT COUNT(p) FROM tb_product p WHERE p.deletedAt IS NULL AND p.quantity > p.minStock")
    fun countQuantityIsGreaterThanMinStock(): Int
}
