package org.popug.tracker.accounting.service.rates

object Rates {

    fun penaltyRate() = (10..20).random().toBigDecimal()

    fun awardRate() = (20..40).random().toBigDecimal()
}
