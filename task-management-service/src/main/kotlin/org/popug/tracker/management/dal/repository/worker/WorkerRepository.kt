package org.popug.tracker.management.dal.repository.worker

import org.popug.tracker.management.dal.model.worker.Worker
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface WorkerRepository : JpaRepository<Worker, Long> {

    fun findByPublicId(publicId: String): Optional<Worker>
}
