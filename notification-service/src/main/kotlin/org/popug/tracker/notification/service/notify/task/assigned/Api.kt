package org.popug.tracker.notification.service.notify.task.assigned

object Api {

    data class Request(
        val publicId: String,
        val userPublicId: String,
        val description: String,
    )
}
