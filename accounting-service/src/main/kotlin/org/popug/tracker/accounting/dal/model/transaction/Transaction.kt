package org.popug.tracker.accounting.dal.model.transaction

import org.popug.tracker.accounting.dal.model.account.Account
import org.popug.tracker.core.dal.common.PublicIdentifier
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
sealed class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val publicId: String = PublicIdentifier.new(),
    @ManyToOne var account: Account,

    val createdAt: LocalDateTime = LocalDateTime.now()
) {

    enum class TransactionType {
        DEBIT, CREDIT, PAYOUT
    }
}
