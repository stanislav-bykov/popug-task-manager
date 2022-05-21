package org.popug.tracker.accounting.dal.model.transaction

import org.popug.tracker.accounting.dal.model.account.Account
import org.popug.tracker.accounting.dal.model.task.Task
import java.math.BigDecimal
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
@DiscriminatorValue("CREDIT")
data class CreditTransaction(
    @ManyToOne val task: Task,
    override var account: Account,
    val credit: BigDecimal = BigDecimal.ZERO,
) : Transaction(account = account)
