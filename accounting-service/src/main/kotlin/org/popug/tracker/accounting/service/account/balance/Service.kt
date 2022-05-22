package org.popug.tracker.accounting.service.account.balance

import org.popug.tracker.accounting.dal.model.account.Account
import org.popug.tracker.accounting.dal.model.transaction.CreditTransaction
import org.popug.tracker.accounting.dal.model.transaction.DebitTransaction
import org.popug.tracker.accounting.dal.model.transaction.PayoutTransaction
import org.popug.tracker.accounting.dal.model.transaction.Transaction
import org.popug.tracker.accounting.dal.model.transaction.Transaction.TransactionType
import org.popug.tracker.accounting.dal.repository.account.AccountRepository
import org.popug.tracker.accounting.dal.repository.transaction.TransactionRepository
import org.popug.tracker.accounting.service.account.balance.Api.Response
import org.popug.tracker.core.business.BusinessService
import org.springframework.stereotype.Service

@Service
class Service(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) : BusinessService<Api.Request, Response> {

    override fun invoke(request: Api.Request): Response =
        accountRepository.findByPublicId(request.userPublicId)
            .map { account ->
                val accountTransactions = transactionRepository.findByAccount(account)
                toResponse(account, accountTransactions)
            }.orElseThrow()

    private fun toResponse(account: Account, accountTransactions: Collection<Transaction>) =
        Response(
            balance = account.walletAmount,
            operations = accountTransactions.groupBy({ it.createdAt.toLocalDate() }, {
                when (it) {
                    is PayoutTransaction -> Response.Operation(
                        type = TransactionType.CREDIT,
                        description = "Money has been sent to your bank card",
                        amount = it.credit
                    )
                    is CreditTransaction -> Response.Operation(
                        type = TransactionType.DEBIT,
                        description = it.task.description,
                        amount = it.credit
                    )
                    is DebitTransaction -> Response.Operation(
                        type = TransactionType.PAYOUT,
                        description = it.task.description,
                        amount = it.debit
                    )
                }
            })
        )
}
