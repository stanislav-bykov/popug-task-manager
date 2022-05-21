package org.popug.tracker.accounting.dal.repository.transaction

import org.popug.tracker.accounting.dal.model.transaction.DebitTransaction
import org.springframework.data.jpa.repository.JpaRepository

interface DebitTransactionRepository : JpaRepository<DebitTransaction, Long>
