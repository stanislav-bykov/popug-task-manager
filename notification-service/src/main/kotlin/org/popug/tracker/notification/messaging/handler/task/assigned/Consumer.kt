package org.popug.tracker.notification.messaging.handler.task.assigned

import mu.KLogging
import org.popug.tracker.core.messaging.MessageSource.BusinessEvent
import org.popug.tracker.core.messaging.business.task.TaskAssignedBusinessEventBody
import org.popug.tracker.notification.service.notify.task.assigned.Api
import org.popug.tracker.notification.service.notify.task.assigned.Service
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@KafkaListener(
    topics = [BusinessEvent.Tasks.TASK_ASSIGNED],
    groupId = "\${spring.kafka.consumer.group-id}",
)
class Consumer(private val notifyService: Service) {

    @KafkaHandler
    fun consume(payload: TaskAssignedBusinessEventBody) {
        logger.info(">>>Received message: $payload")
        notifyService.invoke(payload.toApiRequest())
    }

    companion object : KLogging() {

        fun TaskAssignedBusinessEventBody.toApiRequest() =
            Api.Request(
                publicId = publicId,
                userPublicId = userPublicId,
                description = description,
            )
    }
}
