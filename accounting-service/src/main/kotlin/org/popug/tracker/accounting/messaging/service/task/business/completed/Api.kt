package org.popug.tracker.accounting.messaging.service.task.business.completed

object Api {

    data class Request(
        val taskPublicId: String,
        val userPublicId: String,
    )
}
