package br.com.pgtel.pgtelbackend.modules.stock.infra.gateway.jpa

import br.com.pgtel.pgtelbackend.modules.stock.infra.entity.ProductEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface JPAProductRepository : JpaRepository<ProductEntity, UUID> {


    @Query("SELECT p FROM tb_product p WHERE p.deletedAt IS NULL AND lower(p.name) LIKE lower(concat('%', :name, '%')) ORDER BY p.name ASC")
    fun findByNameContainingIgnoreCaseAndDeletedAtIsNull(name: String, pageable: PageRequest): Page<ProductEntity>

    @Query("SELECT p FROM tb_product p WHERE p.deletedAt IS NULL ORDER BY p.name ASC")
    fun findAllByDeletedAtIsNull(pageable: PageRequest): Page<ProductEntity>
    fun findByIdAndDeletedAtIsNull(id: UUID): Optional<ProductEntity>
    fun countByDeletedAtIsNull(): Int
    @Query("SELECT COUNT(p) FROM tb_product p WHERE p.deletedAt IS NULL AND p.quantity < p.minStock")
    fun countQuantityIsLessThanMinStock(): Int
    @Query("SELECT COUNT(p) FROM tb_product p WHERE p.deletedAt IS NULL AND p.quantity > p.minStock")
    fun countQuantityIsGreaterThanMinStock(): Int
}
