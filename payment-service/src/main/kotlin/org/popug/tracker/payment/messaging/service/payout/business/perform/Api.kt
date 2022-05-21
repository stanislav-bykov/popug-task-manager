package org.popug.tracker.payment.messaging.service.payout.business.perform

import java.math.BigDecimal

object Api {

    data class Request(
        val txnPublicId: String,
        val userPublicId: String,
        val amount: BigDecimal
    )
}
