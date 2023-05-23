package br.com.pgtel.pgtelbackend.modules.stock.domain.errors

import br.com.pgtel.pgtelbackend.core.exceptions.Error

object ProductErrors {
    val PRODUCT_NOT_FOUND = Error("PD-002", "Product not found")
    val INSUFFICIENT_STOCK = Error("PD-003", "Insufficient stock")
    val Product_ALREADY_DELETED = Error("PD-004", "Product already deleted")
    val PRODUCT_NAME_INVALID = Error("PD-005", "Product name invalid")
    val PRODUCT_UNIT_PRICE_INVALID = Error("PD-006", "Product unit price invalid")
    val PRODUCT_QUANTITY_INVALID = Error("PD-007", "Product quantity invalid")
    val PRODUCT_MIN_STOCK_INVALID = Error("PD-008", "Product min stock invalid")
}
