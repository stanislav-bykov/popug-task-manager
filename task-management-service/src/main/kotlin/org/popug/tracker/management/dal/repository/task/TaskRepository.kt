package org.popug.tracker.management.dal.repository.task

import org.popug.tracker.management.dal.model.task.Task
import org.popug.tracker.management.dal.model.task.Task.TaskStatus
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long> {

    fun findAllByStatus(status: TaskStatus): Collection<Task>

    fun findAllAssigned() = findAllByStatus(TaskStatus.ASSIGNED)

    fun findAllByWorkerPublicIdAndStatus(publicId: String, status: TaskStatus): Collection<Task>

    fun findAllAssignedByWorkerPublicId(publicId: String) =
        findAllByWorkerPublicIdAndStatus(publicId, TaskStatus.ASSIGNED)
}
