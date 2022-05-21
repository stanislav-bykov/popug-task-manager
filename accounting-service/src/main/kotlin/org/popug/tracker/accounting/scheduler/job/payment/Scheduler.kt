package org.popug.tracker.accounting.scheduler.job.payment

import mu.KLogging
import org.popug.tracker.accounting.scheduler.job.SchedulerEnv.AdminPayoutFormingJob.JOB_NAME
import org.popug.tracker.accounting.scheduler.job.SchedulerEnv.AdminPayoutFormingJob.SCHEDULER
import org.popug.tracker.core.scheduler.annotation.SchedulerComponent
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.scheduling.annotation.Scheduled
import org.popug.tracker.accounting.service.admin.payout.forming.Service as PayoutFormingService

@SchedulerComponent
@ConditionalOnProperty(
    value = ["$SCHEDULER.enabled"],
    matchIfMissing = false,
    havingValue = "true"
)
class Scheduler(private val service: PayoutFormingService) {

    @Scheduled(cron = "\${$SCHEDULER.schedule}")
    fun start() {
        logger.info("$JOB_NAME has been started")
        service.invoke(Unit)
        logger.info("$JOB_NAME has been finished")
    }

    private companion object : KLogging()
}
