package org.popug.tracker.accounting.service.admin.profit

import org.popug.tracker.accounting.dal.model.transaction.CreditTransaction
import org.popug.tracker.accounting.dal.model.transaction.DebitTransaction
import org.popug.tracker.accounting.dal.repository.transaction.CreditTransactionRepository
import org.popug.tracker.accounting.dal.repository.transaction.DebitTransactionRepository
import org.popug.tracker.accounting.service.admin.profit.Api.Response.DailyProfit
import org.popug.tracker.core.business.BusinessService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Service
class Service(
    private val creditTransactionRepository: CreditTransactionRepository,
    private val debitTransactionRepository: DebitTransactionRepository
) : BusinessService<Unit, Api.Response> {

    @Transactional
    override fun invoke(request: Unit) =
        (creditTransactionRepository.findAll() + debitTransactionRepository.findAll())
            .groupBy { it.createdAt.toLocalDate() }
            .map { (date, transactions) ->
                val credit = transactions.filterIsInstance<CreditTransaction>().sumOf { it.credit }
                val debit = transactions.filterIsInstance<DebitTransaction>().sumOf { it.debit }
                DailyProfit(date = date, amount = (credit - debit))
            }.run { toResponse(this) }

    private fun toResponse(profits: Collection<DailyProfit>): Api.Response {
        return Optional.ofNullable(
            profits.find { it.date == LocalDate.now() }
        ).map {
            Api.Response(today = it, daily = profits.filter { profit -> it != profit })
        }.orElseGet {
            Api.Response(today = DailyProfit(date = LocalDate.now(), amount = BigDecimal.ZERO), daily = profits)
        }
    }
}
