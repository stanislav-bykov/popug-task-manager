package org.popug.tracker.core.messaging

object MessageSource {

    private const val USERS_PREFIX = "users"
    private const val TASKS_PREFIX = "tasks"

    object Streaming {
        const val USERS_STREAM = "$USERS_PREFIX-stream"
        const val TASKS_STREAM = "$TASKS_PREFIX-stream"
    }

    object BusinessEvent {
        object Users {
            const val USER_REGISTERED = "$USERS_PREFIX.user_registered"
        }

        object Tasks {
            const val TASK_ASSIGNED = "$TASKS_PREFIX.task_assigned"
        }
    }
}
