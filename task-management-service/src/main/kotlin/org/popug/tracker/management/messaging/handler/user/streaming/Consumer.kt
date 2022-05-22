package org.popug.tracker.management.messaging.handler.user.streaming

import mu.KLogging
import org.popug.tracker.core.messaging.MessageSource.Streaming
import org.popug.tracker.core.messaging.streaming.user.CreatedUserStreamingMessageBody
import org.popug.tracker.core.messaging.streaming.user.DeletedUserStreamingMessageBody
import org.popug.tracker.core.messaging.streaming.user.UpdatedUserStreamingMessageBody
import org.popug.tracker.core.messaging.streaming.user.UserStreamingMessageBody
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.popug.tracker.management.streaming.service.user.create.Api as CreateUserApi
import org.popug.tracker.management.streaming.service.user.create.Service as CreateUserService
import org.popug.tracker.management.streaming.service.user.delete.Api as DeleteUserApi
import org.popug.tracker.management.streaming.service.user.delete.Service as DeleteUserService
import org.popug.tracker.management.streaming.service.user.update.Api as UpdateUserApi
import org.popug.tracker.management.streaming.service.user.update.Service as UpdateUserService

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
    fun consume(payload: UserStreamingMessageBody) {
        logger.info(">>>Received message: $payload")
        when (payload) {
            is CreatedUserStreamingMessageBody -> createService.invoke(payload.toCreateApiRequest())
            is UpdatedUserStreamingMessageBody -> updateService.invoke(payload.toUpdateApiRequest())
            is DeletedUserStreamingMessageBody -> deleteService.invoke(payload.toDeleteApiRequest())
        }
    }

    companion object : KLogging() {

        fun CreatedUserStreamingMessageBody.toCreateApiRequest() =
            CreateUserApi.Request(
                publicId = publicId,
                firstName = firstName,
                lastName = lastName,
                role = role
            )

        fun UpdatedUserStreamingMessageBody.toUpdateApiRequest() =
            UpdateUserApi.Request(
                publicId = publicId,
                firstName = firstName,
                lastName = lastName,
                role = role
            )

        fun DeletedUserStreamingMessageBody.toDeleteApiRequest() =
            DeleteUserApi.Request(publicId = publicId)
    }
}
