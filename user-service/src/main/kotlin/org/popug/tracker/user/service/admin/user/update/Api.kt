package org.popug.tracker.user.service.admin.user.update

object Api {

    data class Request(
        val id: String,
        val body: RequestBody
    )

    data class RequestBody(
        val firstName: String,
        val lastName: String,
        val email: String
    )

    data class Response(
        val id: String,
        val firstName: String,
        val lastName: String,
        val email: String
    )
}
