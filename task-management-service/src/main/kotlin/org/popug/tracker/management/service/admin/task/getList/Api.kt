package org.popug.tracker.management.service.admin.task.getList

object Api {

    data class Response(
        val data: Collection<TaskData>
    ) {
        data class TaskData(
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
}
