package org.popug.tracker.accounting.service.account.balance

import org.popug.tracker.accounting.dal.model.transaction.Transaction.TransactionType
import java.math.BigDecimal
import java.time.LocalDate

object Api {

    data class Request(val userPublicId: String)

    data class Response(
        val balance: BigDecimal,
        val operations: Map<LocalDate, Collection<Operation>>
    ) {
        data class Operation(
            val amount: BigDecimal,
            val description: String?,
            val type: TransactionType,
        )
    }
}
