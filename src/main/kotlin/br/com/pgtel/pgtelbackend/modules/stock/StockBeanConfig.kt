package br.com.pgtel.pgtelbackend.modules.stock

import br.com.pgtel.pgtelbackend.modules.stock.gateway.MovementGateway
import br.com.pgtel.pgtelbackend.modules.stock.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.gateway.ProductUnitGateway
import br.com.pgtel.pgtelbackend.modules.stock.infrastructure.gateway.DefaultMovementGateway
import br.com.pgtel.pgtelbackend.modules.stock.infrastructure.gateway.DefaultProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.infrastructure.gateway.DefaultProductUnitGateway
import br.com.pgtel.pgtelbackend.modules.stock.infrastructure.gateway.jpa.JPAMovementRepository
import br.com.pgtel.pgtelbackend.modules.stock.infrastructure.gateway.jpa.JPAProductRepository
import br.com.pgtel.pgtelbackend.modules.stock.infrastructure.gateway.jpa.JPAProductUnitRepository
import br.com.pgtel.pgtelbackend.modules.stock.usecase.dashboard.productsStatistic.ProductsStatisticUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.create.CreateMovementUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.delete.DeleteMovementUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.movement.findPageable.FindPageableMovementUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.create.CreateProductUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.delete.DeleteProductByIdUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.findById.FindProductByIdUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.product.findPageable.FindProductsPageableUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.create.CreateProductUnitUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.delete.DeleteUnitByIdUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.productUnit.findAll.FindAllProductUnitsUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StockBeanConfig {

    @Bean
    fun productGateway(
        jpaProductRepository: JPAProductRepository,
    ): ProductGateway {
        return DefaultProductGateway(
            jpaProductRepository
        )
    }


    @Bean
    fun createProductUseCase(productGateway: ProductGateway, unitGateway: ProductUnitGateway): CreateProductUseCase {
        return CreateProductUseCase(
            productGateway,
            unitGateway
        )
    }

    @Bean
    fun findProductByIdUseCase(gateway: ProductGateway): FindProductByIdUseCase {
        return FindProductByIdUseCase(
            gateway
        )
    }

    @Bean
    fun findProductsPageableUseCase(gateway: ProductGateway): FindProductsPageableUseCase {
        return FindProductsPageableUseCase(
            gateway
        )
    }

    @Bean
    fun deleteProductByIdUseCase(gateway: ProductGateway): DeleteProductByIdUseCase {
        return DeleteProductByIdUseCase(
            gateway
        )
    }

    @Bean
    fun productUnitGateway(jpaProductUnitRepository: JPAProductUnitRepository): ProductUnitGateway {
        return DefaultProductUnitGateway(
            jpaProductUnitRepository
        )
    }

    @Bean
    fun createProductUnitUseCase(gateway: ProductUnitGateway): CreateProductUnitUseCase {
        return CreateProductUnitUseCase(
            gateway
        )
    }

    @Bean
    fun findAllProductUnitsUseCase(gateway: ProductUnitGateway): FindAllProductUnitsUseCase {
        return FindAllProductUnitsUseCase(
            gateway
        )
    }

    @Bean
    fun deleteUnitByIdUseCase(gateway: ProductUnitGateway): DeleteUnitByIdUseCase {
        return DeleteUnitByIdUseCase(
            gateway
        )
    }


    @Bean
    fun movementGateway(jpaMovementRepository: JPAMovementRepository): MovementGateway {
        return DefaultMovementGateway(
            jpaMovementRepository
        )
    }

    @Bean
    fun findPageableMovementUseCase(gateway: MovementGateway): FindPageableMovementUseCase {
        return FindPageableMovementUseCase(
            gateway
        )
    }

    @Bean
    fun createMovementUseCase(productGateway: ProductGateway, movementGateway: MovementGateway): CreateMovementUseCase {
        return CreateMovementUseCase(
            productGateway,
            movementGateway
        )
    }

    @Bean
    fun deleteMovementUseCase(movementGateway: MovementGateway, productGateway: ProductGateway): DeleteMovementUseCase {
        return DeleteMovementUseCase(
            movementGateway,
            productGateway
        )
    }

    @Bean
    fun productsStatisticUseCase(productGateway: ProductGateway): ProductsStatisticUseCase {
        return ProductsStatisticUseCase(
            productGateway
        )
    }

}