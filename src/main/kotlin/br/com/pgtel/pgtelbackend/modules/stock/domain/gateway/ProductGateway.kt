package br.com.pgtel.pgtelbackend.modules.stock.domain.gateway

import br.com.pgtel.pgtelbackend.modules.stock.domain.entities.Product
import org.springframework.data.domain.Page
import java.util.*

interface ProductGateway {

    fun save(product: Product)
    fun findById(id: UUID): Product?
    fun findProductsPageable(page: Int, size: Int, name: String? = null): Page<Product>
    fun countAll(): Int
    fun countAllOutOfStock(): Int
    fun countAllInStockWithHighQuantity(): Int
}


