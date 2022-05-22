package org.popug.tracker.management.service.admin.task.shuffle

import org.popug.tracker.core.business.UnitBusinessService
import org.popug.tracker.management.dal.model.task.Task
import org.popug.tracker.management.dal.model.worker.Worker
import org.popug.tracker.management.dal.repository.task.TaskRepository
import org.popug.tracker.management.dal.repository.worker.WorkerRepository
import org.popug.tracker.messaging.MessageProducer
import org.popug.tracker.messaging.business.task.TaskAssignedBusinessEvent
import org.popug.tracker.messaging.streaming.task.CreatedTaskStreamingMessagePayload
import org.popug.tracker.messaging.streaming.task.TaskStreamingMessage
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRES_NEW
import org.springframework.transaction.annotation.Transactional

@Service
class Service(
    private val workerRepository: WorkerRepository,
    private val taskRepository: TaskRepository,
    private val messageProducer: MessageProducer
) : UnitBusinessService {

    @Transactional
    override fun invoke(request: Unit) {
        val workers = workerRepository.findAllActiveWorkers()
        taskRepository.findAll()
            .forEach { task -> randomTaskReassign(task, workers) }
    }

    @Transactional(propagation = REQUIRES_NEW)
    fun randomTaskReassign(task: Task, workers: Collection<Worker>) {
        // TODO: 14.05.2022 atomicity
        task.worker = workers.random()
        messageProducer.send(task.toStreamingMessage())
        messageProducer.send(task.toBusinessEvent())
    }

    companion object {

        fun Task.toStreamingMessage() = TaskStreamingMessage(
            CreatedTaskStreamingMessagePayload(
                publicId = publicId,
                userPublicId = worker.publicId,
                description = description
            )
        )

        fun Task.toBusinessEvent() = TaskAssignedBusinessEvent(
            TaskAssignedBusinessEvent.TaskAssignedBusinessEventPayload(
                taskPublicId = publicId,
                userPublicId = worker.publicId,
            )
        )
    }
}
