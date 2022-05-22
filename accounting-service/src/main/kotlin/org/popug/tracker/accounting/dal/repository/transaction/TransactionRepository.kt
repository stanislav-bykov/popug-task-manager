package org.popug.tracker.accounting.dal.repository.transaction

import org.popug.tracker.accounting.dal.model.account.Account
import org.popug.tracker.accounting.dal.model.transaction.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findByAccount(account: Account): Collection<Transaction>
}
