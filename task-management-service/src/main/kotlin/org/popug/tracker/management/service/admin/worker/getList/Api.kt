package org.popug.tracker.management.service.admin.worker.getList

import org.popug.tracker.core.dal.common.model.user.UserRole
import org.popug.tracker.management.dal.model.worker.Worker

object Api {

    data class Response(
        val data: Collection<Data>
    ) {
        data class Data(
            val id: Long,
            val publicId: String,
            val firstName: String,
            val lastName: String,
            val role: UserRole,
            val status: Worker.WorkerStatus
        )
    }
}
