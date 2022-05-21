package org.popug.tracker.core.messaging.streaming.task

import org.popug.tracker.core.messaging.Message
import org.popug.tracker.core.messaging.MessageSource.Streaming
import org.popug.tracker.core.messaging.streaming.StreamingMessage
import org.popug.tracker.core.messaging.streaming.StreamingMessagePayload

data class TaskStreamingMessage(override val payload: TaskStreamingMessagePayload) :
    StreamingMessage<TaskStreamingMessagePayload> {

    override val metadata: Message.Metadata =
        Message.Metadata(key = payload.publicId, destinationTopic = Streaming.TASKS_STREAM)
}

sealed class TaskStreamingMessagePayload : StreamingMessagePayload

data class CreatedTaskStreamingMessagePayload(
    override val publicId: String,
    val userPublicId: String,
    val description: String
) : TaskStreamingMessagePayload()

data class UpdatedTaskStreamingMessagePayload(
    override val publicId: String,
    val userPublicId: String,
    val description: String,
) : TaskStreamingMessagePayload()

data class DeletedTaskStreamingMessagePayload(override val publicId: String) : TaskStreamingMessagePayload()
