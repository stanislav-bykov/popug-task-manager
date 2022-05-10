package org.popug.tracker.user.service.admin.user.getList

object Api {

    data class Response(
        val data: Collection<Data>
    ) {
        data class Data(
            val id: String,
            val firstName: String?,
            val lastName: String?,
            val email: String?
        )
    }
}
