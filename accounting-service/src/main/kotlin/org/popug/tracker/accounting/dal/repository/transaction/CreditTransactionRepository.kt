package org.popug.tracker.accounting.dal.repository.transaction

import org.popug.tracker.accounting.dal.model.transaction.CreditTransaction
import org.springframework.data.jpa.repository.JpaRepository

interface CreditTransactionRepository : JpaRepository<CreditTransaction, Long>
