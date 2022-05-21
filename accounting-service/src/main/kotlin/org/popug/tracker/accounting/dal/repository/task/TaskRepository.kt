package org.popug.tracker.accounting.dal.repository.task

import org.popug.tracker.accounting.dal.model.task.Task
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TaskRepository : JpaRepository<Task, Long> {

    fun findByPublicId(publicId: String): Optional<Task>
}
