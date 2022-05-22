package org.popug.tracker.management.service.admin.task.create

import org.popug.tracker.core.business.BusinessService
import org.popug.tracker.core.messaging.MessageProducer
import org.popug.tracker.core.messaging.business.task.TaskAssignedBusinessEvent
import org.popug.tracker.core.messaging.business.task.TaskAssignedBusinessEventBody
import org.popug.tracker.core.messaging.streaming.task.CreatedTaskStreamingMessageBody
import org.popug.tracker.core.messaging.streaming.task.TaskStreamingMessage
import org.popug.tracker.management.dal.model.task.Task
import org.popug.tracker.management.dal.model.worker.Worker
import org.popug.tracker.management.dal.repository.task.TaskRepository
import org.popug.tracker.management.dal.repository.worker.WorkerRepository
import org.popug.tracker.management.service.admin.task.create.Api.Response.WorkerData
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Service(
    private val taskRepository: TaskRepository,
    private val workerRepository: WorkerRepository,
    private val messageProducer: MessageProducer
) : BusinessService<Api.Request, Api.Response> {

    @Transactional
    override fun invoke(request: Api.Request): Api.Response = with(request.body) {
        val task = workerRepository.findById(workerId)
            .map { worker -> toTask(worker) }
            .map { task -> taskRepository.save(task) }
            .orElseThrow()
        // TODO: 14.05.2022 atomicity
        messageProducer.send(task.toStreamingMessage())
        messageProducer.send(task.toBusinessEvent())
        toResponse(task)
    }

    private companion object {

        fun Api.RequestBody.toTask(worker: Worker) =
            Task(
                worker = worker,
                description = description
            )

        fun toResponse(task: Task) = with(task) {
            Api.Response(
                id = task.id,
                description = task.description,
                credit = task.credit,
                debit = task.debit,
                worker = WorkerData(
                    id = worker.id,
                    firstName = worker.firstName,
                    lastName = worker.lastName
                )
            )
        }

        fun Task.toStreamingMessage() = TaskStreamingMessage(
            CreatedTaskStreamingMessageBody(
                publicId = publicId,
                userPublicId = worker.publicId,
                description = description,
                credit = credit,
                debit = debit
            )
        )

        fun Task.toBusinessEvent() = TaskAssignedBusinessEvent(
            TaskAssignedBusinessEventBody(
                publicId = publicId,
                userPublicId = worker.publicId,
                description = description
            )
        )
    }
}
