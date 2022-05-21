package org.popug.tracker.core.messaging.streaming

import org.popug.tracker.core.messaging.Message

interface StreamingMessage<T : StreamingMessagePayload> : Message {
    override val payload: T
}

interface StreamingMessagePayload : Message.MessagePayload {
    val publicId: String
}
