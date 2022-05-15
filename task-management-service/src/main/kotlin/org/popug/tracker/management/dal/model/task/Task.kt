package org.popug.tracker.management.dal.model.task

import org.popug.tracker.core.dal.common.PublicIdentifier
import org.popug.tracker.management.common.dal.Auditable
import org.popug.tracker.management.dal.model.task.Task.TaskStatus.ACTIVE
import org.popug.tracker.management.dal.model.worker.Worker
import org.popug.tracker.management.service.admin.task.rates.Rates.credit
import org.popug.tracker.management.service.admin.task.rates.Rates.debit
import javax.persistence.*

@Entity
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val publicId: String = PublicIdentifier.new(),
    @ManyToOne var worker: Worker,
    var description: String,
    val credit: Int = credit(),
    val debit: Int = debit(),
    @Enumerated(EnumType.STRING)
    var status: TaskStatus = ACTIVE
) : Auditable() {

    enum class TaskStatus {
        ACTIVE,
        COMPLETED
    }
}
