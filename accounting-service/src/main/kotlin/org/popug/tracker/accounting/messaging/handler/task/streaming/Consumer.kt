package org.popug.tracker.accounting.messaging.handler.task.streaming

import mu.KLogging
import org.popug.tracker.core.messaging.MessageSource.Streaming
import org.popug.tracker.core.messaging.streaming.task.CreatedTaskStreamingMessagePayload
import org.popug.tracker.core.messaging.streaming.task.DeletedTaskStreamingMessagePayload
import org.popug.tracker.core.messaging.streaming.task.TaskStreamingMessagePayload
import org.popug.tracker.core.messaging.streaming.task.UpdatedTaskStreamingMessagePayload
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.popug.tracker.accounting.messaging.service.task.streaming.create.Api as CreateTaskApi
import org.popug.tracker.accounting.messaging.service.task.streaming.create.Service as CreateTaskService
import org.popug.tracker.accounting.messaging.service.task.streaming.delete.Api as DeleteTaskApi
import org.popug.tracker.accounting.messaging.service.task.streaming.delete.Service as DeleteTaskService
import org.popug.tracker.accounting.messaging.service.task.streaming.update.Api as UpdateTaskApi
import org.popug.tracker.accounting.messaging.service.task.streaming.update.Service as UpdateTaskService

@Component
@KafkaListener(
    topics = [Streaming.TASKS_STREAM],
    groupId = "\${spring.kafka.consumer.group-id}",
)
class Consumer(
    private val createService: CreateTaskService,
    private val updateService: UpdateTaskService,
    private val deleteService: DeleteTaskService
) {

    @KafkaHandler
    fun consume(payload: TaskStreamingMessagePayload) {
        logger.info(">>>Received message: $payload")
        when (payload) {
            is CreatedTaskStreamingMessagePayload -> createService.invoke(payload.toCreateApiRequest())
            is UpdatedTaskStreamingMessagePayload -> updateService.invoke(payload.toUpdateApiRequest())
            is DeletedTaskStreamingMessagePayload -> deleteService.invoke(payload.toDeleteApiRequest())
        }
    }

    companion object : KLogging() {

        fun CreatedTaskStreamingMessagePayload.toCreateApiRequest() =
            CreateTaskApi.Request(
                publicId = publicId,
                userPublicId = userPublicId,
                description = description
            )

        fun UpdatedTaskStreamingMessagePayload.toUpdateApiRequest() =
            UpdateTaskApi.Request(
                publicId = publicId,
                userPublicId = userPublicId,
                description = description
            )

        fun DeletedTaskStreamingMessagePayload.toDeleteApiRequest() =
            DeleteTaskApi.Request(publicId = publicId)
    }
}
