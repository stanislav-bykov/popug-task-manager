package org.popug.tracker.management.service.tasks.complete

object Api {

    data class Request(
        val taskId: Long,
        val userPublicId: String
    )
}
