package org.popug.tracker.management.streaming.service.user.delete

import org.popug.tracker.core.business.BusinessService
import org.popug.tracker.management.dal.model.worker.Worker
import org.popug.tracker.management.dal.model.worker.Worker.WorkerStatus.DISABLED
import org.popug.tracker.management.dal.repository.worker.WorkerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Service(
    private val workerRepository: WorkerRepository
) : BusinessService<Api.Request, Unit> {

    @Transactional
    override fun invoke(request: Api.Request) {
        workerRepository.findByPublicId(request.publicId)
            .map { it.delete() }
    }

    private companion object {
        fun Worker.delete() {
            status = DISABLED
        }
    }
}
