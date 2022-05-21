package org.popug.tracker.payment.messaging.service.payout.business.perform

import org.popug.tracker.core.business.BusinessService
import org.popug.tracker.core.messaging.MessageProducer
import org.popug.tracker.core.messaging.business.payout.PayoutPerformedBusinessEvent
import org.popug.tracker.core.messaging.business.payout.PayoutPerformedBusinessEvent.PayoutPerformedBusinessEventPayload
import org.springframework.stereotype.Service

@Service
class Service(
    private val messageProducer: MessageProducer
) : BusinessService<Api.Request, Unit> {

    override fun invoke(request: Api.Request) = with(request) {
        // TODO: 14.05.2022 perform payout
        messageProducer.send(
            PayoutPerformedBusinessEvent(
                PayoutPerformedBusinessEventPayload(
                    txnPublicId = txnPublicId,
                    userPublicId = userPublicId,
                    amount = amount
                )
            )
        )
    }
}
