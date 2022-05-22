package org.popug.tracker.accounting.messaging.handler.task.streaming

import mu.KLogging
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.popug.tracker.messaging.MessageSource.Streaming
import org.popug.tracker.messaging.SchemaMatcher
import org.popug.tracker.messaging.streaming.task.*
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.popug.tracker.accounting.messaging.service.task.streaming.create.Api as CreateTaskApi
import org.popug.tracker.accounting.messaging.service.task.streaming.create.Service as CreateTaskService
import org.popug.tracker.accounting.messaging.service.task.streaming.update.Api as UpdateTaskApi
import org.popug.tracker.accounting.messaging.service.task.streaming.update.Service as UpdateTaskService

@Component
class Consumer(
    private val createService: CreateTaskService,
    private val updateService: UpdateTaskService,
) {

    @KafkaListener(topics = [Streaming.TASKS_STREAM], groupId = "\${spring.kafka.consumer.group-id}")
    fun consume(record: ConsumerRecord<String, TaskStreamingMessagePayload>) {
        logger.info(">>>Received message: $record")
        SchemaMatcher.match(record)

        when (val payload = record.value()) {
            is CreatedTaskStreamingMessagePayload -> createService.invoke(payload.toCreateApiRequest())
            is UpdatedTaskStreamingMessagePayload -> updateService.invoke(payload.toUpdateApiRequest())
            is CreatedTaskStreamingMessageExpandedPayload -> Unit // TODO: 22.05.2022  
            is UpdatedTaskStreamingMessageExpandedPayload -> Unit // TODO: 22.05.2022  
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
    }
}
