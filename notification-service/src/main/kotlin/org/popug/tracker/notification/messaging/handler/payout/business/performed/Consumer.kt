package org.popug.tracker.notification.messaging.handler.payout.business.performed

import mu.KLogging
import org.popug.tracker.core.messaging.MessageSource.Business
import org.popug.tracker.core.messaging.business.payout.PayoutPerformedBusinessEvent.PayoutPerformedBusinessEventPayload
import org.popug.tracker.notification.messaging.service.payout.business.notify.Api
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.popug.tracker.notification.messaging.service.payout.business.notify.Service as NotifyService

@Component
@KafkaListener(
    topics = [Business.Payout.PAYOUT_PERFORMED],
    groupId = "\${spring.kafka.consumer.group-id}",
)
class Consumer(private val service: NotifyService) {

    @KafkaHandler
    fun consume(payload: PayoutPerformedBusinessEventPayload) {
        logger.info(">>>Received message: $payload")
        service.invoke(payload.toApiRequest())
    }

    companion object : KLogging() {

        fun PayoutPerformedBusinessEventPayload.toApiRequest() =
            Api.Request(
                txnPublicId = txnPublicId,
                userPublicId = userPublicId,
                amount = amount
            )
    }
}
