package org.popug.tracker.management.service.tasks.complete

import org.popug.tracker.core.business.BusinessService
import org.popug.tracker.management.dal.model.task.Task.TaskStatus
import org.popug.tracker.management.dal.repository.task.TaskRepository
import org.springframework.stereotype.Service

@Service
class Service(
    private val taskRepository: TaskRepository
) : BusinessService<Api.Request, Unit> {

    override fun invoke(request: Api.Request): Unit = with(request) {
        taskRepository.findById(taskId)
            .filter { task -> task.worker.publicId == userPublicId }
            .map { task -> task.status == TaskStatus.COMPLETED }
    }
}
