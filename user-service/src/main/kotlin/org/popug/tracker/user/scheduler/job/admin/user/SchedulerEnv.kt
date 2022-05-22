package org.popug.tracker.user.scheduler.job.admin.user

object SchedulerEnv {

    object AdminUserRegisterJob {
        const val JOB_NAME: String = "admin-user-register"
        const val SCHEDULER = "scheduler.job.$JOB_NAME"
    }

    object AdminUserResetPasswordJob {
        const val JOB_NAME: String = "admin-user-reset-password"
        const val SCHEDULER = "scheduler.job.$JOB_NAME"
    }

    object AdminUserRoleAssignJob {
        const val JOB_NAME: String = "admin-user-role-assign"
        const val SCHEDULER = "scheduler.job.$JOB_NAME"
    }

    object AdminUserConfigureJob {
        const val JOB_NAME: String = "admin-user-configure"
        const val SCHEDULER = "scheduler.job.$JOB_NAME"
    }

    object AdminUserActivateJob {
        const val JOB_NAME: String = "admin-user-activate"
        const val SCHEDULER = "scheduler.job.$JOB_NAME"
    }
}
