package br.com.pgtel.pgtelbackend.modules.stock.infrastructure.gateway

import br.com.pgtel.pgtelbackend.modules.stock.domain.Product
import br.com.pgtel.pgtelbackend.modules.stock.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.infrastructure.entity.ProductEntity
import br.com.pgtel.pgtelbackend.modules.stock.infrastructure.gateway.jpa.JPAProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import java.util.*

class DefaultProductGateway(
    private val productRepository: JPAProductRepository,
) : ProductGateway {

    override fun save(product: Product) {
        productRepository.save(ProductEntity.fromDomain(product))
    }

    override fun findById(id: UUID): Product? {
        productRepository.findByIdAndDeletedAtIsNull(id).let {
            if (it.isPresent) {
                return it.get().toDomain()
            }
        }
        return null
    }

    override fun findProductsPageable(page: Int, size: Int, name: String?): Page<Product> {
        val pageable = PageRequest.of(page, size)
        if (name.isNullOrBlank()) {
            return productRepository.findAllByDeletedAtIsNull(pageable).map { it.toDomain() }
        }
        return productRepository.findByNameContainingIgnoreCaseAndDeletedAtIsNull(name, pageable).map { it.toDomain() }
    }

    override fun countAll(): Int {
        return productRepository.countByDeletedAtIsNull()
    }

    override fun countAllOutOfStock(): Int {
        return productRepository.countQuantityIsLessThanMinStock()
    }

    override fun countAllInStockWithHighQuantity(): Int {
        return productRepository.countQuantityIsGreaterThanMinStock()
    }

}