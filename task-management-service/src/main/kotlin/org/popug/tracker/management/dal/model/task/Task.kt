package org.popug.tracker.management.dal.model.task

import org.popug.tracker.core.dal.common.PublicIdentifier
import org.popug.tracker.management.common.dal.Auditable
import org.popug.tracker.management.dal.model.task.Task.TaskStatus.ASSIGNED
import org.popug.tracker.management.dal.model.worker.Worker
import javax.persistence.*

@Entity
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val publicId: String = PublicIdentifier.new(),
    @ManyToOne var worker: Worker,
    var description: String,
    @Enumerated(EnumType.STRING)
    var status: TaskStatus = ASSIGNED
) : Auditable() {

    fun workerPublicId() = worker.publicId

    fun complete() {
        status = TaskStatus.COMPLETED
    }

    enum class TaskStatus { ASSIGNED, COMPLETED }
}
