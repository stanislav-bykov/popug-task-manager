package org.popug.tracker.payment.messaging.handler.payout.business.formed

import mu.KLogging
import org.popug.tracker.core.messaging.MessageSource.Business
import org.popug.tracker.core.messaging.business.payout.PayoutFormedBusinessEvent.PayoutFormedBusinessEventPayload
import org.popug.tracker.payment.messaging.service.payout.business.perform.Api
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.popug.tracker.payment.messaging.service.payout.business.perform.Service as PerformPayoutService

@Component
@KafkaListener(
    topics = [Business.Payout.PAYOUT_FORMED],
    groupId = "\${spring.kafka.consumer.group-id}",
)
class Consumer(private val service: PerformPayoutService) {

    @KafkaHandler
    fun consume(payload: PayoutFormedBusinessEventPayload) {
        logger.info(">>>Received message: $payload")
        service.invoke(payload.toApiRequest())
    }

    companion object : KLogging() {

        fun PayoutFormedBusinessEventPayload.toApiRequest() =
            Api.Request(
                txnPublicId = txnPublicId,
                userPublicId = userPublicId,
                amount = amount
            )
    }
}
