package br.com.pgtel.pgtelbackend.web.controller

import br.com.pgtel.pgtelbackend.modules.stock.usecase.dashboard.productsStatistic.ProductsStatisticUseCase
import br.com.pgtel.pgtelbackend.modules.stock.usecase.dashboard.productsStatistic.dto.ProductsStatisticOutputUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dashboard")
class DashboardController(
    private val productsStatisticUseCase: ProductsStatisticUseCase
) {

    @GetMapping("/products-statistic")
    fun productsStatistic(): ProductsStatisticOutputUseCase {
        return productsStatisticUseCase.execute()
    }

}