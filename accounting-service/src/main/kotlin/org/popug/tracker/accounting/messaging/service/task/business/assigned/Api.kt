package org.popug.tracker.accounting.messaging.service.task.business.assigned

object Api {

    data class Request(
        val taskPublicId: String,
        val userPublicId: String,
    )
}
