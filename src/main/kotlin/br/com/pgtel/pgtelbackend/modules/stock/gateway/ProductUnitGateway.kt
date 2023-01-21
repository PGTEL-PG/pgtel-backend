package br.com.pgtel.pgtelbackend.modules.stock.gateway

import br.com.pgtel.pgtelbackend.modules.stock.domain.ProductUnit
import java.util.*

interface ProductUnitGateway {

    fun save(unit: ProductUnit)

    fun findById(id: UUID): ProductUnit?
    fun findAll(): Set<ProductUnit>

}
