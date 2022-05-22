package org.popug.tracker.core.messaging

interface Message {
    val metadata: Metadata
    val body: MessageBody

    data class Metadata(
        val key: String,
        val destinationTopic: String
    )

    interface MessageBody {
        val publicId: String
    }
}
