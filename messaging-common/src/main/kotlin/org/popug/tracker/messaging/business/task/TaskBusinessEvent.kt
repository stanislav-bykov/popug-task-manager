package org.popug.tracker.messaging.business.task

import org.popug.tracker.messaging.Message
import org.popug.tracker.messaging.MessageSource.Business.Tasks.TASK_ASSIGNED
import org.popug.tracker.messaging.MessageSource.Business.Tasks.TASK_COMPLETED
import org.popug.tracker.messaging.business.BusinessEvent
import org.popug.tracker.messaging.business.BusinessEventPayload
import org.popug.tracker.messaging.business.task.TaskAssignedBusinessEvent.TaskAssignedBusinessEventPayload
import org.popug.tracker.messaging.business.task.TaskCompletedBusinessEvent.TaskCompletedBusinessEventPayload

data class TaskAssignedBusinessEvent(override val payload: TaskAssignedBusinessEventPayload) :
    BusinessEvent<TaskAssignedBusinessEventPayload> {

    override val metadata: Message.Metadata =
        Message.Metadata(key = "${payload.taskPublicId}_${payload.userPublicId}", destinationTopic = TASK_ASSIGNED)

    data class TaskAssignedBusinessEventPayload(
        val taskPublicId: String,
        val userPublicId: String
    ) : BusinessEventPayload
}

data class TaskCompletedBusinessEvent(override val payload: TaskCompletedBusinessEventPayload) :
    BusinessEvent<TaskCompletedBusinessEventPayload> {

    override val metadata: Message.Metadata =
        Message.Metadata(key = "${payload.taskPublicId}_${payload.userPublicId}", destinationTopic = TASK_COMPLETED)

    data class TaskCompletedBusinessEventPayload(
        val taskPublicId: String,
        val userPublicId: String
    ) : BusinessEventPayload
}
