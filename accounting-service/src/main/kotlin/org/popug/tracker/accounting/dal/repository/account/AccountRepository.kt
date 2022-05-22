package org.popug.tracker.accounting.dal.repository.account

import org.popug.tracker.accounting.dal.model.account.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface AccountRepository : JpaRepository<Account, Long> {

    fun findByPublicId(publicId: String): Optional<Account>

    @Query("select a from Account a where a.walletAmount > 0")
    fun findAllWithPositiveWalletAmount(): Collection<Account>
}
