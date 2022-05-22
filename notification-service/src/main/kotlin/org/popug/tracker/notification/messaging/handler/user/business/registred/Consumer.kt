package org.popug.tracker.notification.messaging.handler.user.business.registred

import mu.KLogging
import org.popug.tracker.messaging.MessageSource.Business
import org.popug.tracker.messaging.business.user.UserRegisteredBusinessEvent.UserRegisteredBusinessEventPayload
import org.popug.tracker.notification.messaging.service.user.business.notify.Api
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.popug.tracker.notification.messaging.service.user.business.notify.Service as NotifyService

@Component
@KafkaListener(
    topics = [Business.Users.USER_REGISTERED],
    groupId = "\${spring.kafka.consumer.group-id}",
)
class Consumer(private val service: NotifyService) {

    @KafkaHandler
    fun consume(payload: UserRegisteredBusinessEventPayload) {
        logger.info(">>>Received message: $payload")
        service.invoke(payload.toApiRequest())
    }

    companion object : KLogging() {

        fun UserRegisteredBusinessEventPayload.toApiRequest() =
            Api.Request(
                userPublicId = userPublicId,
                firstName = firstName,
                lastName = lastName,
                email = email
            )
    }
}
