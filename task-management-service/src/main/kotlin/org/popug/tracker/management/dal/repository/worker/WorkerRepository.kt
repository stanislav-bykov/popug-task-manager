package org.popug.tracker.management.dal.repository.worker

import org.popug.tracker.core.dal.common.model.user.UserRole
import org.popug.tracker.management.dal.model.worker.Worker
import org.popug.tracker.management.dal.model.worker.Worker.WorkerStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface WorkerRepository : JpaRepository<Worker, Long> {

    fun findByPublicId(publicId: String): Optional<Worker>

    fun findAllByStatusAndRole(status: WorkerStatus, role: UserRole): Collection<Worker>

    fun findAllActiveWorkers() = findAllByStatusAndRole(status = WorkerStatus.ACTIVE, role = UserRole.USER)
}
