package br.com.pgtel.pgtelbackend.modules.stock.infrastructure.gateway

import br.com.pgtel.pgtelbackend.modules.stock.domain.ProductUnit
import br.com.pgtel.pgtelbackend.modules.stock.errors.ProductUnitErrors
import br.com.pgtel.pgtelbackend.core.exceptions.BusinessException
import br.com.pgtel.pgtelbackend.modules.stock.gateway.ProductUnitGateway
import br.com.pgtel.pgtelbackend.modules.stock.infrastructure.entity.ProductUnitEntity
import br.com.pgtel.pgtelbackend.modules.stock.infrastructure.gateway.jpa.JPAProductUnitRepository
import java.util.*

class DefaultProductUnitGateway(
    private val unitRepository: JPAProductUnitRepository
) : ProductUnitGateway {

    override fun save(unit: ProductUnit) {
        val exists = unitRepository.existsByNameIgnoreCase(unit.name)
        if(exists) throw BusinessException(ProductUnitErrors.PRODUCT_UNIT_NAME_ALREADY_EXISTS)
        val unitEntity = ProductUnitEntity.fromDomain(unit)
        unitRepository.save(unitEntity)
    }

    override fun findById(id: UUID): ProductUnit? {
        unitRepository.findByIdAndDeletedAtIsNull(id).let {
            if (it.isPresent) {
                return it.get().toDomain()
            }
        }
        return null
    }

    override fun findAll(): Set<ProductUnit> {
        return unitRepository.findAllByDeletedAtIsNull().map { it.toDomain() }.toSet()
    }


}