package org.popug.tracker.user.service.admin.user.create

import org.popug.tracker.core.dal.common.model.user.UserRole

object Api {

    data class Request(
        val body: RequestBody
    )

    data class RequestBody(
        val username: String,
        val firstName: String,
        val lastName: String,
        val email: String,
        val role: UserRole
    )

    data class Response(
        val id: Long,
    )
}
