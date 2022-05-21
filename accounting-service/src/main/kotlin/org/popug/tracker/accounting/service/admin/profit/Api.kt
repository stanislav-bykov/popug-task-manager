package org.popug.tracker.accounting.service.admin.profit

import java.math.BigDecimal
import java.time.LocalDate

object Api {

    data class Request(val userPublicId: String)

    data class Response(
        val today: DailyProfit,
        val daily: Collection<DailyProfit>
    ) {
        data class DailyProfit(
            val date: LocalDate,
            val amount: BigDecimal,
        )
    }
}
