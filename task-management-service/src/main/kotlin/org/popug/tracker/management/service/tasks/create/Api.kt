package org.popug.tracker.management.service.tasks.create

object Api {

    data class Request(
        val body: RequestBody
    )

    data class RequestBody(
        val workerId: Long,
        val description: String
    )

    data class Response(
        val id: Long,
        val description: String,
        val worker: WorkerData,
    ) {
        data class WorkerData(
            val id: Long,
            val firstName: String,
            val lastName: String,
        )
    }
}
