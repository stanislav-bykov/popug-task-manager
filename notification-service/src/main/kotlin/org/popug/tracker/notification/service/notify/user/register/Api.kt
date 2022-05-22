package org.popug.tracker.notification.service.notify.user.register

object Api {

    data class Request(
        val publicId: String,
        val firstName: String,
        val lastName: String,
        val email: String
    )
}
