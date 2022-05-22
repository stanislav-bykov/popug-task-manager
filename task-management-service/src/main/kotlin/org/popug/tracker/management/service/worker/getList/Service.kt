package org.popug.tracker.management.service.worker.getList

import org.popug.tracker.core.business.BusinessService
import org.popug.tracker.management.dal.model.worker.Worker
import org.popug.tracker.management.dal.repository.worker.WorkerRepository
import org.springframework.stereotype.Service

@Service
class Service(
    private val workerRepository: WorkerRepository
) : BusinessService<Unit, Api.Response> {

    override fun invoke(request: Unit) = toResponse(workerRepository.findAll())

    private fun toResponse(workers: Collection<Worker>) =
        workers.map { it.toResponse() }.run { Api.Response(this) }

    companion object {
        private fun Worker.toResponse() =
            Api.Response.Data(
                id = id,
                publicId = publicId,
                firstName = firstName,
                lastName = lastName,
                role = role,
                status = status
            )
    }
}
