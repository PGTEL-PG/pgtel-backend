package br.com.pgtel.pgtelbackend.modules.stock.errors

import br.com.pgtel.pgtelbackend.core.exceptions.Error

object ProductUnitErrors {

    val PRODUCT_UNIT_NAME_INVALID = Error("PDU-001", "Product unit name is invalid")
    val PRODUCT_UNIT_ALREADY_DELETED = Error("PDU-002", "Product unit already deleted")
    val PRODUCT_UNIT_NAME_ALREADY_EXISTS = Error("PDU-003", "Product unit name already exists")
}
