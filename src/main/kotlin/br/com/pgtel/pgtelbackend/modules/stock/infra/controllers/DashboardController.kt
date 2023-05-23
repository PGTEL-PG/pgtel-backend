package br.com.pgtel.pgtelbackend.modules.stock.infra.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dashboard")
class DashboardController(
    private val productsStatisticUseCase: br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.dashboard.productsStatistic.ProductsStatisticUseCase
) {

    @GetMapping("/products-statistic")
    fun productsStatistic(): br.com.pgtel.pgtelbackend.modules.stock.domain.usecase.dashboard.productsStatistic.dto.ProductsStatisticOutputUseCase {
        return productsStatisticUseCase.execute()
    }

}