package org.popug.tracker.management.service.tasks.complete

import org.popug.tracker.core.business.BusinessService
import org.popug.tracker.management.dal.model.task.Task
import org.popug.tracker.management.dal.repository.task.TaskRepository
import org.popug.tracker.messaging.MessageProducer
import org.popug.tracker.messaging.business.task.TaskCompletedBusinessEvent
import org.popug.tracker.messaging.business.task.TaskCompletedBusinessEvent.TaskCompletedBusinessEventPayload
import org.popug.tracker.messaging.streaming.task.TaskStreamingMessage
import org.popug.tracker.messaging.streaming.task.UpdatedTaskStreamingMessagePayload
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Service(
    private val messageProducer: MessageProducer,
    private val taskRepository: TaskRepository
) : BusinessService<Api.Request, Unit> {

    @Transactional
    override fun invoke(request: Api.Request): Unit = with(request) {
        taskRepository.findById(taskId)
            .filter { task -> task.workerPublicId() == userPublicId }
            .ifPresent { task ->
                task.complete()
                messageProducer.send(task.toStreamingMessage())
                messageProducer.send(task.toBusinessEvent())
            }
    }

    private companion object {
        fun Task.toStreamingMessage() = TaskStreamingMessage(
            UpdatedTaskStreamingMessagePayload(
                publicId = publicId,
                userPublicId = worker.publicId,
                description = description
            )
        )

        fun Task.toBusinessEvent() = TaskCompletedBusinessEvent(
            TaskCompletedBusinessEventPayload(taskPublicId = publicId, userPublicId = workerPublicId())
        )
    }
}
