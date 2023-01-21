package br.com.pgtel.pgtelbackend.modules.stock.errors

import br.com.pgtel.pgtelbackend.core.exceptions.Error

object MovementErrors {
    val MOVEMENT_NOT_FOUND = Error("MV-001", "Movement not found")
    val MOVEMENT_STOCK_IS_NOT_AVAILABLE = Error("MV-002", "Stock is not available")
    val PRODUCT_NOT_FOUND = Error("MV-003", "Product not found")
    val QUANTITY_INVALID = Error("MV-004", "Quantity must be greater than zero")
    val UNIT_PRICE_INVALID = Error("MV-005", "Unit price must be greater than zero")
    val MOVEMENT_CANNOT_BE_NEGATIVE_QUANTITY =
        Error("MV-006", "Movement cannot be removed as it will have its negative amount")
    val UNIT_PRICE_IS_REQUIRED = Error("MV-007", "Unit price is required")
}