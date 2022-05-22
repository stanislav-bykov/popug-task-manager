package org.popug.tracker.messaging

interface Message {
    val metadata: Metadata
    val payload: MessagePayload

    data class Metadata(
        val key: String,
        val destinationTopic: String
    )

    interface MessagePayload

    object MessageHeader {
        const val SCHEMA_SOURCE = "SCHEMA_SOURCE"
    }
}
