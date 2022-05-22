package org.popug.tracker.core.messaging.streaming

import org.popug.tracker.core.messaging.Message

interface StreamingMessage<T : StreamingMessageBody> : Message {
    override val body: T
}

interface StreamingMessageBody : Message.MessageBody
