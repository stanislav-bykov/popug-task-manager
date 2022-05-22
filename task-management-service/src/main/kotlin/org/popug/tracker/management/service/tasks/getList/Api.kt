package org.popug.tracker.management.service.tasks.getList

object Api {

    data class Request(
        val userPublicId: String
    )

    data class Response(
        val data: Collection<Data>
    ) {
        data class Data(
            val id: Long,
            val description: String,
        )
    }
}
