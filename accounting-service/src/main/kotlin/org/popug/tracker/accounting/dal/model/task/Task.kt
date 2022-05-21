package org.popug.tracker.accounting.dal.model.task

import org.popug.tracker.accounting.common.dal.Auditable
import org.popug.tracker.accounting.service.rates.Rates
import org.popug.tracker.core.dal.common.PublicIdentifier
import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val publicId: String = PublicIdentifier.new(),
    var userPublicId: String,
    var description: String? = null,
    val penaltyRate: BigDecimal = Rates.penaltyRate(),
    val awardRate: BigDecimal = Rates.awardRate(),
) : Auditable()
