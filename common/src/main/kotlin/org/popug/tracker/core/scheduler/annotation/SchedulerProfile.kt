package org.popug.tracker.core.scheduler.annotation

import org.springframework.context.annotation.Profile

private const val SCHEDULER_PROFILE_NAME = "scheduler"

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Profile(SCHEDULER_PROFILE_NAME)
annotation class SchedulerProfile
