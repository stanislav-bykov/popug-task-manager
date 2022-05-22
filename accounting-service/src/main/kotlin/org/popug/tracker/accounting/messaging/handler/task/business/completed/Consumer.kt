package org.popug.tracker.accounting.messaging.handler.task.business.completed

import mu.KLogging
import org.popug.tracker.accounting.messaging.service.task.business.completed.Api
import org.popug.tracker.accounting.messaging.service.task.business.completed.Service
import org.popug.tracker.messaging.MessageSource.Business
import org.popug.tracker.messaging.business.task.TaskCompletedBusinessEvent.TaskCompletedBusinessEventPayload
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@KafkaListener(
    topics = [Business.Tasks.TASK_COMPLETED],
    groupId = "\${spring.kafka.consumer.group-id}",
)
class Consumer(
    private val service: Service,
) {

    @KafkaHandler
    fun consume(payload: TaskCompletedBusinessEventPayload) {
        logger.info(">>>Received message: $payload")
        service.invoke(payload.toApiRequest())
    }

    companion object : KLogging() {

        fun TaskCompletedBusinessEventPayload.toApiRequest() =
            Api.Request(taskPublicId = taskPublicId, userPublicId = userPublicId)
    }
}
