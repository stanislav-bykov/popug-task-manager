package org.popug.tracker.core.messaging

object MessageSource {

    private const val USERS_PREFIX = "users"
    private const val TASKS_PREFIX = "tasks"
    private const val PAYOUT_PREFIX = "payout"

    object Streaming {
        const val USERS_STREAM = "$USERS_PREFIX-stream"
        const val TASKS_STREAM = "$TASKS_PREFIX-stream"
    }

    object Business {
        object Users {
            const val USER_REGISTERED = "$USERS_PREFIX.user_registered"
        }

        object Tasks {
            const val TASK_ASSIGNED = "$TASKS_PREFIX.task_assigned"
            const val TASK_COMPLETED = "$TASKS_PREFIX.task_completed"
        }

        object Payout {
            const val PAYOUT_FORMED = "$PAYOUT_PREFIX.payout_formed"
            const val PAYOUT_PERFORMED = "$PAYOUT_PREFIX.payout_performed"
        }
    }
}
