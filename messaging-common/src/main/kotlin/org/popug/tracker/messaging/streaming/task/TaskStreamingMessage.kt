package org.popug.tracker.messaging.streaming.task

import org.popug.tracker.messaging.Message
import org.popug.tracker.messaging.MessageSource.Streaming
import org.popug.tracker.messaging.schema.SchemaSource
import org.popug.tracker.messaging.schema.SchemaSourceVersion.Streaming.TaskStreaming.Source
import org.popug.tracker.messaging.streaming.StreamingMessage
import org.popug.tracker.messaging.streaming.StreamingMessagePayload

data class TaskStreamingMessage(override val payload: TaskStreamingMessagePayload) :
    StreamingMessage<TaskStreamingMessagePayload> {

    override val metadata: Message.Metadata =
        Message.Metadata(key = payload.publicId, destinationTopic = Streaming.TASKS_STREAM)
}

sealed class TaskStreamingMessagePayload : StreamingMessagePayload

@SchemaSource(Source.V1)
data class CreatedTaskStreamingMessagePayload(
    override val publicId: String,
    val userPublicId: String,
    val description: String
) : TaskStreamingMessagePayload()

@SchemaSource(Source.V1)
data class UpdatedTaskStreamingMessagePayload(
    override val publicId: String,
    val userPublicId: String,
    val description: String,
) : TaskStreamingMessagePayload()

@SchemaSource(Source.V2)
data class CreatedTaskStreamingMessageExpandedPayload(
    override val publicId: String,
    val userPublicId: String,
    val jiraId: String,
    val title: String
) : TaskStreamingMessagePayload()

@SchemaSource(Source.V2)
data class UpdatedTaskStreamingMessageExpandedPayload(
    override val publicId: String,
    val userPublicId: String,
    val jiraId: String,
    val title: String
) : TaskStreamingMessagePayload()
