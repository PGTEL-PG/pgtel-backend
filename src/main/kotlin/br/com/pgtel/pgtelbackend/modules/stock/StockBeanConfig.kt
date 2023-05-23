package br.com.pgtel.pgtelbackend.modules.stock

import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.FileUploaderGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.MovementGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.gateway.ProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.delete.DeleteMovementUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.findPageable.FindPageableMovementUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.create.CreateProductUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.delete.DeleteProductByIdUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.findById.FindProductByIdUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.findPageable.FindProductsPageableUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.update.UpdateProductUseCase
import br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.product.uploadImage.UploadProductImageUseCase
import br.com.pgtel.pgtelbackend.modules.stock.infra.gateway.DefaultMovementGateway
import br.com.pgtel.pgtelbackend.modules.stock.infra.gateway.DefaultProductGateway
import br.com.pgtel.pgtelbackend.modules.stock.infra.gateway.LocalFileUploaderGateway
import br.com.pgtel.pgtelbackend.modules.stock.infra.gateway.jpa.JPAMovementRepository
import br.com.pgtel.pgtelbackend.modules.stock.infra.gateway.jpa.JPAProductRepository
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
    fun createProductUseCase(productGateway: ProductGateway): CreateProductUseCase {
        return CreateProductUseCase(
            productGateway,
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
    fun createMovementUseCase(
        productGateway: ProductGateway,
        movementGateway: MovementGateway
    ): br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.create.CreateMovementUseCase {
        return br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.movement.create.CreateMovementUseCase(
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
    fun productsStatisticUseCase(productGateway: ProductGateway): br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.dashboard.productsStatistic.ProductsStatisticUseCase {
        return br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.dashboard.productsStatistic.ProductsStatisticUseCase(
            productGateway
        )
    }

    @Bean
    fun updateProductUseCase(productGateway: ProductGateway): UpdateProductUseCase {
        return UpdateProductUseCase(
            productGateway
        )
    }

    @Bean
    fun fileUploaderGateway(): FileUploaderGateway {
        return LocalFileUploaderGateway()
    }

    @Bean
    fun uploadProductImageUseCase(
        productGateway: ProductGateway,
        fileUploaderGateway: FileUploaderGateway
    ): UploadProductImageUseCase {
        return UploadProductImageUseCase(
            productGateway,
            fileUploaderGateway
        )
    }

}