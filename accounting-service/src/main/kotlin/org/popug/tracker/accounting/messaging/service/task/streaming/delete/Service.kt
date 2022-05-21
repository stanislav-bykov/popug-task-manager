package org.popug.tracker.accounting.messaging.service.task.streaming.delete

import org.popug.tracker.accounting.dal.repository.task.TaskRepository
import org.popug.tracker.core.business.BusinessService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Service(
    private val taskRepository: TaskRepository
) : BusinessService<Api.Request, Unit> {

    @Transactional
    override fun invoke(request: Api.Request) {
        taskRepository.findByPublicId(request.publicId)
            .ifPresent { // TODO: 19.05.2022 delete
            }
    }

    private companion object
}
