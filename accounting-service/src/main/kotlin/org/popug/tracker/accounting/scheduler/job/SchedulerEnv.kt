package org.popug.tracker.accounting.scheduler.job

object SchedulerEnv {

    object AdminPayoutFormingJob {
        const val JOB_NAME: String = "admin-payout-forming"
        const val SCHEDULER = "scheduler.job.$JOB_NAME"
    }
}
