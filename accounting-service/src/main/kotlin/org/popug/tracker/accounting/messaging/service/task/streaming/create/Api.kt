package org.popug.tracker.accounting.messaging.service.task.streaming.create

object Api {

    data class Request(
        val publicId: String,
        val userPublicId: String,
        val description: String
    )
}
