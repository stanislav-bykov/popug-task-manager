package org.popug.tracker.notification.messaging.service.task.business.notify

object Api {

    data class Request(
        val taskPublicId: String,
        val userPublicId: String
    )
}
