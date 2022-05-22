package org.popug.tracker.accounting.messaging.service.task.streaming.update

object Api {

    data class Request(
        val publicId: String,
        val userPublicId: String,
        val description: String,
    )
}
