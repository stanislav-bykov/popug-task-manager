package org.popug.tracker.core.messaging

interface Message {
    val metadata: Metadata
    val payload: MessagePayload

    data class Metadata(
        val key: String,
        val destinationTopic: String
    )

    interface MessagePayload
}
