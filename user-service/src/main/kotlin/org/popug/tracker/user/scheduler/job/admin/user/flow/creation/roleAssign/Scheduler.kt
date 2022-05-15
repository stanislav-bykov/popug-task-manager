package org.popug.tracker.user.scheduler.job.admin.user.flow.creation.roleAssign

import mu.KLogging
import org.popug.tracker.core.scheduler.annotation.SchedulerComponent
import org.popug.tracker.user.scheduler.job.admin.user.SchedulerEnv.AdminUserRoleAssignJob.JOB_NAME
import org.popug.tracker.user.scheduler.job.admin.user.SchedulerEnv.AdminUserRoleAssignJob.SCHEDULER
import org.popug.tracker.user.service.admin.user.roleAssign.Service
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.scheduling.annotation.Scheduled

@SchedulerComponent
@ConditionalOnProperty(
    value = ["$SCHEDULER.enabled"],
    matchIfMissing = false,
    havingValue = "true"
)
class Scheduler(private val roleAssignService: Service) {

    @Scheduled(cron = "\${$SCHEDULER.schedule}")
    fun start() {
        logger.info("$JOB_NAME has been started")
        roleAssignService.invoke(Unit)
        logger.info("$JOB_NAME has been finished")
    }

    private companion object : KLogging()
}
