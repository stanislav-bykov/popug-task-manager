package org.popug.tracker.notification.messaging.service.payout.business.notify

import java.math.BigDecimal

object Api {

    data class Request(
        val txnPublicId: String,
        val userPublicId: String,
        val amount: BigDecimal
    )
}
