package org.popug.tracker.core.messaging.streaming.user

import org.popug.tracker.core.dal.common.model.user.UserRole
import org.popug.tracker.core.messaging.Message
import org.popug.tracker.core.messaging.MessageSource.Streaming
import org.popug.tracker.core.messaging.streaming.StreamingMessage
import org.popug.tracker.core.messaging.streaming.StreamingMessageBody

data class UserStreamingMessage(override val body: UserStreamingMessageBody) :
    StreamingMessage<UserStreamingMessageBody> {

    override val metadata: Message.Metadata =
        Message.Metadata(key = body.publicId, destinationTopic = Streaming.USERS_STREAM)
}

sealed class UserStreamingMessageBody : StreamingMessageBody

data class CreatedUserStreamingMessageBody(
    override val publicId: String,
    val firstName: String,
    val lastName: String,
    val role: UserRole,
    val email: String
) : UserStreamingMessageBody()

data class UpdatedUserStreamingMessageBody(
    override val publicId: String,
    val firstName: String,
    val lastName: String,
    val role: UserRole,
    val email: String
) : UserStreamingMessageBody()

data class DeletedUserStreamingMessageBody(override val publicId: String) : UserStreamingMessageBody()
