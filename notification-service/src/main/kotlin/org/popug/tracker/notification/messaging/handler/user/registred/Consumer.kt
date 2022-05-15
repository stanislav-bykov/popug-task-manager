package org.popug.tracker.notification.messaging.handler.user.registred

import mu.KLogging
import org.popug.tracker.core.messaging.MessageSource.BusinessEvent
import org.popug.tracker.core.messaging.business.user.UserRegisteredBusinessEventBody
import org.popug.tracker.notification.service.notify.user.register.Api
import org.popug.tracker.notification.service.notify.user.register.Service
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@KafkaListener(
    topics = [BusinessEvent.Users.USER_REGISTERED],
    groupId = "\${spring.kafka.consumer.group-id}",
)
class Consumer(private val notifyService: Service) {

    @KafkaHandler
    fun consume(payload: UserRegisteredBusinessEventBody) {
        logger.info(">>>Received message: $payload")
        notifyService.invoke(payload.toApiRequest())
    }

    companion object : KLogging() {

        fun UserRegisteredBusinessEventBody.toApiRequest() =
            Api.Request(
                publicId = publicId,
                firstName = firstName,
                lastName = lastName,
                email = email
            )
    }
}
