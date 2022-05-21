package org.popug.tracker.notification.messaging.service.user.business.notify

object Api {

    data class Request(
        val userPublicId: String,
        val firstName: String,
        val lastName: String,
        val email: String
    )
}
