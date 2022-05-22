package org.popug.tracker.core.messaging.streaming.task

import org.popug.tracker.core.messaging.Message
import org.popug.tracker.core.messaging.MessageSource.Streaming
import org.popug.tracker.core.messaging.streaming.StreamingMessage
import org.popug.tracker.core.messaging.streaming.StreamingMessageBody

data class TaskStreamingMessage(override val body: TaskStreamingMessageBody) :
    StreamingMessage<TaskStreamingMessageBody> {

    override val metadata: Message.Metadata =
        Message.Metadata(key = body.publicId, destinationTopic = Streaming.TASKS_STREAM)
}

sealed class TaskStreamingMessageBody : StreamingMessageBody

data class CreatedTaskStreamingMessageBody(
    override val publicId: String,
    val userPublicId: String,
    val description: String,
    val credit: Int,
    val debit: Int
) : TaskStreamingMessageBody()

data class UpdatedTaskStreamingMessageBody(
    override val publicId: String,
    val userPublicId: String,
    val description: String,
    val credit: Int,
    val debit: Int
) : TaskStreamingMessageBody()

data class DeletedTaskStreamingMessageBody(override val publicId: String) : TaskStreamingMessageBody()
