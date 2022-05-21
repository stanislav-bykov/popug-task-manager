package org.popug.tracker.accounting.service.admin.payout.forming

import org.popug.tracker.accounting.dal.model.account.Account
import org.popug.tracker.accounting.dal.model.transaction.PayoutTransaction
import org.popug.tracker.accounting.dal.repository.account.AccountRepository
import org.popug.tracker.accounting.dal.repository.transaction.TransactionRepository
import org.popug.tracker.core.business.UnitBusinessService
import org.popug.tracker.core.messaging.MessageProducer
import org.popug.tracker.core.messaging.business.payout.PayoutFormedBusinessEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class Service(
    private val messageProducer: MessageProducer,
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) : UnitBusinessService {

    @Transactional
    override fun invoke(request: Unit) =
        accountRepository.findAllWithPositiveWalletAmount()
            .forEach { account ->
                val transaction = createPayoutTransaction(account = account)
                messageProducer.send(transaction.toBusinessEvent())
                account.walletAmount = BigDecimal.ZERO
            }

    fun createPayoutTransaction(account: Account) =
        transactionRepository.save(PayoutTransaction(account = account, credit = account.walletAmount))

    private companion object {
        fun PayoutTransaction.toBusinessEvent() =
            PayoutFormedBusinessEvent(
                PayoutFormedBusinessEvent.PayoutFormedBusinessEventPayload(
                    txnPublicId = publicId,
                    userPublicId = account.publicId,
                    amount = credit
                )
            )
    }
}
