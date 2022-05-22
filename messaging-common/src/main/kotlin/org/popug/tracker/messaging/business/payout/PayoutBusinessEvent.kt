package org.popug.tracker.messaging.business.payout

import org.popug.tracker.messaging.Message
import org.popug.tracker.messaging.MessageSource.Business.Payout.PAYOUT_FORMED
import org.popug.tracker.messaging.MessageSource.Business.Payout.PAYOUT_PERFORMED
import org.popug.tracker.messaging.business.BusinessEvent
import org.popug.tracker.messaging.business.BusinessEventPayload
import org.popug.tracker.messaging.business.payout.PayoutFormedBusinessEvent.PayoutFormedBusinessEventPayload
import org.popug.tracker.messaging.business.payout.PayoutPerformedBusinessEvent.PayoutPerformedBusinessEventPayload
import java.math.BigDecimal

data class PayoutFormedBusinessEvent(override val payload: PayoutFormedBusinessEventPayload) :
    BusinessEvent<PayoutFormedBusinessEventPayload> {

    override val metadata: Message.Metadata =
        Message.Metadata(key = "${payload.txnPublicId}_${payload.userPublicId}", destinationTopic = PAYOUT_FORMED)

    data class PayoutFormedBusinessEventPayload(
        val txnPublicId: String,
        val userPublicId: String,
        val amount: BigDecimal
    ) : BusinessEventPayload
}

data class PayoutPerformedBusinessEvent(override val payload: PayoutPerformedBusinessEventPayload) :
    BusinessEvent<PayoutPerformedBusinessEventPayload> {

    override val metadata: Message.Metadata =
        Message.Metadata(key = "${payload.txnPublicId}_${payload.userPublicId}", destinationTopic = PAYOUT_PERFORMED)

    data class PayoutPerformedBusinessEventPayload(
        val txnPublicId: String,
        val userPublicId: String,
        val amount: BigDecimal
    ) : BusinessEventPayload
}
