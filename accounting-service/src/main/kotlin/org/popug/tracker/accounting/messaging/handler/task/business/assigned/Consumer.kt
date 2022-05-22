package org.popug.tracker.accounting.messaging.handler.task.business.assigned

import mu.KLogging
import org.popug.tracker.accounting.messaging.service.task.business.assigned.Api
import org.popug.tracker.accounting.messaging.service.task.business.assigned.Service
import org.popug.tracker.messaging.MessageSource.Business
import org.popug.tracker.messaging.business.task.TaskAssignedBusinessEvent.TaskAssignedBusinessEventPayload
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@KafkaListener(
    topics = [Business.Tasks.TASK_ASSIGNED],
    groupId = "\${spring.kafka.consumer.group-id}",
)
class Consumer(
    private val service: Service,
) {

    @KafkaHandler
    fun consume(payload: TaskAssignedBusinessEventPayload) {
        logger.info(">>>Received message: $payload")
        service.invoke(payload.toApiRequest())
    }

    companion object : KLogging() {

        fun TaskAssignedBusinessEventPayload.toApiRequest() =
            Api.Request(
                taskPublicId = taskPublicId,
                userPublicId = userPublicId,
            )
    }
}
