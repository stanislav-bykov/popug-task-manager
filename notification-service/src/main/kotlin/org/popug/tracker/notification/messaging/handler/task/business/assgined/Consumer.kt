package org.popug.tracker.notification.messaging.handler.task.business.assgined

import mu.KLogging
import org.popug.tracker.core.messaging.MessageSource.Business
import org.popug.tracker.core.messaging.business.task.TaskAssignedBusinessEvent.TaskAssignedBusinessEventPayload
import org.popug.tracker.notification.messaging.service.task.business.notify.Api
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.popug.tracker.notification.messaging.service.task.business.notify.Service as NotifyService

@Component
@KafkaListener(
    topics = [Business.Tasks.TASK_ASSIGNED],
    groupId = "\${spring.kafka.consumer.group-id}",
)
class Consumer(private val service: NotifyService) {

    @KafkaHandler
    fun consume(payload: TaskAssignedBusinessEventPayload) {
        logger.info(">>>Received message: $payload")
        service.invoke(payload.toApiRequest())
    }

    companion object : KLogging() {

        fun TaskAssignedBusinessEventPayload.toApiRequest() =
            Api.Request(
                taskPublicId = taskPublicId,
                userPublicId = userPublicId
            )
    }
}
