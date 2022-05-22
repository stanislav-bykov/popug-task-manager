package org.popug.tracker.user.dal.model.user

import org.popug.tracker.core.dal.common.PublicIdentifier
import org.popug.tracker.core.dal.common.model.user.UserRole
import org.popug.tracker.user.common.dal.Auditable
import org.popug.tracker.user.dal.model.user.UserAccount.AccountStatus.READY_TO_CONFIGURE
import javax.persistence.*

@Entity
data class UserAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val publicId: String = PublicIdentifier.new(),
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    @Enumerated(EnumType.STRING)
    val role: UserRole,
    @Enumerated(EnumType.STRING)
    var status: AccountStatus = READY_TO_CONFIGURE
) : Auditable() {

    enum class AccountStatus {
        READY_TO_CONFIGURE,
        CONFIGURED,
        ACTIVE,
        DISABLED
    }
}
