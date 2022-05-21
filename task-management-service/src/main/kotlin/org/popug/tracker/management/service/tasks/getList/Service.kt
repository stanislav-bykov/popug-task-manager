package org.popug.tracker.management.service.tasks.getList

import org.popug.tracker.core.business.BusinessService
import org.popug.tracker.management.dal.model.task.Task
import org.popug.tracker.management.dal.repository.task.TaskRepository
import org.springframework.stereotype.Service

@Service
class Service(
    private val taskRepository: TaskRepository
) : BusinessService<Api.Request, Api.Response> {

    override fun invoke(request: Api.Request) =
        toResponse(taskRepository.findAllAssignedByWorkerPublicId(publicId = request.userPublicId))

    private fun toResponse(tasks: Collection<Task>) =
        tasks.map { it.toResponse() }.run { Api.Response(this) }

    companion object {
        private fun Task.toResponse() =
            Api.Response.Data(
                id = id,
                description = description
            )
    }
}
