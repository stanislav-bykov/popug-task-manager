package org.popug.tracker.management.dal.model.worker

import org.popug.tracker.core.dal.common.model.user.UserRole
import org.popug.tracker.management.common.dal.Auditable
import org.popug.tracker.management.dal.model.worker.Worker.WorkerStatus.ACTIVE
import org.popug.tracker.management.dal.model.worker.Worker.WorkerStatus.DISABLED
import javax.persistence.*

@Entity
data class Worker(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val publicId: String,
    var firstName: String,
    var lastName: String,
    @Enumerated(EnumType.STRING)
    var role: UserRole,
    @Enumerated(EnumType.STRING)
    var status: WorkerStatus = when (role) {
        UserRole.ADMIN -> DISABLED
        UserRole.USER -> ACTIVE
    }
) : Auditable() {

    enum class WorkerStatus {
        ACTIVE,
        DISABLED
    }
}
