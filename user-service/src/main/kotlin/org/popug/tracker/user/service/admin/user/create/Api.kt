package org.popug.tracker.user.service.admin.user.create

object Api {

    data class Request(
        val username: String,
        val firstName: String,
        val lastName: String,
        val email: String
    )

    data class Response(
        val id: String,
    )
}
