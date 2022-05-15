package org.popug.tracker.core.messaging.business.task

import org.popug.tracker.core.messaging.Message
import org.popug.tracker.core.messaging.MessageSource.BusinessEvent.Tasks.TASK_ASSIGNED
import org.popug.tracker.core.messaging.business.BusinessEvent
import org.popug.tracker.core.messaging.business.BusinessEventBody

data class TaskAssignedBusinessEvent(override val body: TaskAssignedBusinessEventBody) :
    BusinessEvent<TaskAssignedBusinessEventBody> {

    override val metadata: Message.Metadata = Message.Metadata(key = body.publicId, destinationTopic = TASK_ASSIGNED)
}

data class TaskAssignedBusinessEventBody(
    override val publicId: String,
    val userPublicId: String,
    val description: String
) : BusinessEventBody
