package org.popug.tracker.core.scheduler.annotation

import org.springframework.stereotype.Component

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Component
@SchedulerProfile
annotation class SchedulerComponent
