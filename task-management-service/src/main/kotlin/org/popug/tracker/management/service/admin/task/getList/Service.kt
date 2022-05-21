package org.popug.tracker.management.service.admin.task.getList

import org.popug.tracker.core.business.BusinessService
import org.popug.tracker.management.dal.model.task.Task
import org.popug.tracker.management.dal.repository.task.TaskRepository
import org.popug.tracker.management.service.admin.task.getList.Api.Response.TaskData
import org.springframework.stereotype.Service

@Service
class Service(
    private val taskRepository: TaskRepository
) : BusinessService<Unit, Api.Response> {

    override fun invoke(request: Unit) = toResponse(taskRepository.findAllAssigned())

    private fun toResponse(tasks: Collection<Task>) =
        tasks.map { it.toResponse() }.run { Api.Response(this) }

    companion object {
        private fun Task.toResponse() =
            TaskData(
                id = id,
                description = description,
                worker = TaskData.WorkerData(
                    id = worker.id,
                    firstName = worker.firstName,
                    lastName = worker.lastName
                )
            )
    }
}
