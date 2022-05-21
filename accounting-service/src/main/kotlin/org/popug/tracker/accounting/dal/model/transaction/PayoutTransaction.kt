package org.popug.tracker.accounting.dal.model.transaction

import org.popug.tracker.accounting.dal.model.account.Account
import java.math.BigDecimal
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("PAYOUT")
data class PayoutTransaction(
    override var account: Account,
    val credit: BigDecimal = BigDecimal.ZERO,
) : Transaction(account = account)
