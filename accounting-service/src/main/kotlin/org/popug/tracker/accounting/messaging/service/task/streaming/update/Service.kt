package org.popug.tracker.accounting.messaging.service.task.streaming.update

import org.popug.tracker.accounting.dal.model.task.Task
import org.popug.tracker.accounting.dal.repository.task.TaskRepository
import org.popug.tracker.core.business.BusinessService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Service(private val taskRepository: TaskRepository) : BusinessService<Api.Request, Unit> {

    @Transactional
    override fun invoke(request: Api.Request) = with(request) {
        taskRepository.findByPublicId(publicId)
            .ifPresentOrElse(
                { task -> task.update(request) },
                { taskRepository.save(request.toEntity()) }
            )
    }

    private companion object {
        fun Task.update(request: Api.Request) {
            description = request.description
            userPublicId = request.userPublicId
        }

        fun Api.Request.toEntity() =
            Task(
                publicId = publicId,
                userPublicId = userPublicId,
                description = description,
            )
    }
}
