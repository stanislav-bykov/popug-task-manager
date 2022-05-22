package org.popug.tracker.management.messaging.handler.user.streaming

import mu.KLogging
import org.popug.tracker.messaging.MessageSource.Streaming
import org.popug.tracker.messaging.streaming.user.CreatedUserStreamingMessagePayload
import org.popug.tracker.messaging.streaming.user.DeletedUserStreamingMessagePayload
import org.popug.tracker.messaging.streaming.user.UpdatedUserStreamingMessagePayload
import org.popug.tracker.messaging.streaming.user.UserStreamingMessagePayload
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.popug.tracker.management.messaging.service.user.streaming.create.Api as CreateUserApi
import org.popug.tracker.management.messaging.service.user.streaming.create.Service as CreateUserService
import org.popug.tracker.management.messaging.service.user.streaming.delete.Api as DeleteUserApi
import org.popug.tracker.management.messaging.service.user.streaming.delete.Service as DeleteUserService
import org.popug.tracker.management.messaging.service.user.streaming.update.Api as UpdateUserApi
import org.popug.tracker.management.messaging.service.user.streaming.update.Service as UpdateUserService

@Component
@KafkaListener(
    topics = [Streaming.USERS_STREAM],
    groupId = "\${spring.kafka.consumer.group-id}",
)
class Consumer(
    private val createService: CreateUserService,
    private val updateService: UpdateUserService,
    private val deleteService: DeleteUserService
) {

    @KafkaHandler
    fun consume(payload: UserStreamingMessagePayload) {
        logger.info(">>>Received message: $payload")
        when (payload) {
            is CreatedUserStreamingMessagePayload -> createService.invoke(payload.toCreateApiRequest())
            is UpdatedUserStreamingMessagePayload -> updateService.invoke(payload.toUpdateApiRequest())
            is DeletedUserStreamingMessagePayload -> deleteService.invoke(payload.toDeleteApiRequest())
        }
    }

    companion object : KLogging() {

        fun CreatedUserStreamingMessagePayload.toCreateApiRequest() =
            CreateUserApi.Request(
                publicId = publicId,
                firstName = firstName,
                lastName = lastName,
                role = role
            )

        fun UpdatedUserStreamingMessagePayload.toUpdateApiRequest() =
            UpdateUserApi.Request(
                publicId = publicId,
                firstName = firstName,
                lastName = lastName,
                role = role
            )

        fun DeletedUserStreamingMessagePayload.toDeleteApiRequest() =
            DeleteUserApi.Request(publicId = publicId)
    }
}
