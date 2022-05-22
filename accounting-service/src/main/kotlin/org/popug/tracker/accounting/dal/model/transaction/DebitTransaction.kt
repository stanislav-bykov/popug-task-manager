package org.popug.tracker.accounting.dal.model.transaction

import org.popug.tracker.accounting.dal.model.account.Account
import org.popug.tracker.accounting.dal.model.task.Task
import java.math.BigDecimal
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
@DiscriminatorValue("DEBIT")
data class DebitTransaction(
    @ManyToOne val task: Task,
    override var account: Account,
    val debit: BigDecimal = BigDecimal.ZERO,
) : Transaction(account = account)
