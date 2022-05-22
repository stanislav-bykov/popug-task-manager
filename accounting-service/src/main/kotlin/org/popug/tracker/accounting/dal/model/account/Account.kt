package org.popug.tracker.accounting.dal.model.account

import org.popug.tracker.accounting.common.dal.Auditable
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val publicId: String,
    var firstName: String? = null,
    var lastName: String? = null,
    var calculatedAt: LocalDate = LocalDate.now()
) : Auditable() {

    var walletAmount: BigDecimal = BigDecimal.ZERO
        set(value) {
            field = value
            calculatedAt = LocalDate.now()
        }
}
