package org.popug.tracker.core.messaging.streaming.user

import org.popug.tracker.core.dal.common.model.user.UserRole
import org.popug.tracker.core.messaging.Message
import org.popug.tracker.core.messaging.MessageSource.Streaming
import org.popug.tracker.core.messaging.streaming.StreamingMessage
import org.popug.tracker.core.messaging.streaming.StreamingMessagePayload

data class UserStreamingMessage(override val payload: UserStreamingMessagePayload) :
    StreamingMessage<UserStreamingMessagePayload> {

    override val metadata: Message.Metadata =
        Message.Metadata(key = payload.publicId, destinationTopic = Streaming.USERS_STREAM)
}

sealed class UserStreamingMessagePayload : StreamingMessagePayload

data class CreatedUserStreamingMessagePayload(
    override val publicId: String,
    val firstName: String,
    val lastName: String,
    val role: UserRole,
    val email: String
) : UserStreamingMessagePayload()

data class UpdatedUserStreamingMessagePayload(
    override val publicId: String,
    val firstName: String,
    val lastName: String,
    val role: UserRole,
    val email: String
) : UserStreamingMessagePayload()

data class DeletedUserStreamingMessagePayload(override val publicId: String) : UserStreamingMessagePayload()
