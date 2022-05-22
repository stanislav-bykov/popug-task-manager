package org.popug.tracker.user.scheduler.job.admin.user.flow.creation.register

import mu.KLogging
import org.popug.tracker.core.scheduler.annotation.SchedulerComponent
import org.popug.tracker.user.scheduler.job.admin.user.SchedulerEnv.AdminUserRegisterJob.JOB_NAME
import org.popug.tracker.user.scheduler.job.admin.user.SchedulerEnv.AdminUserRegisterJob.SCHEDULER
import org.popug.tracker.user.service.admin.user.register.Service
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.scheduling.annotation.Scheduled

@SchedulerComponent
@ConditionalOnProperty(
    value = ["$SCHEDULER.enabled"],
    matchIfMissing = false,
    havingValue = "true"
)
class Scheduler(private val registerService: Service) {

    @Scheduled(cron = "\${$SCHEDULER.schedule}")
    fun start() {
        logger.info("$JOB_NAME has been started")
        registerService.invoke(Unit)
        logger.info("$JOB_NAME has been finished")
    }

    private companion object : KLogging()
}
