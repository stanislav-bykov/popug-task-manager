package org.popug.tracker.messaging.streaming

import org.popug.tracker.messaging.Message

interface StreamingMessage<T : StreamingMessagePayload> : Message {
    override val payload: T
}

interface StreamingMessagePayload : Message.MessagePayload {
    val publicId: String
}
