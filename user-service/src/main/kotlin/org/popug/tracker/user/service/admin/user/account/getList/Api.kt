package org.popug.tracker.user.service.admin.user.account.getList

import org.popug.tracker.core.dal.common.model.user.UserRole
import org.popug.tracker.user.dal.model.user.UserAccount

object Api {

    data class Response(
        val data: Collection<Data>
    ) {
        data class Data(
            var id: Long,
            val publicId: String,
            val username: String,
            val firstName: String,
            val lastName: String,
            val email: String,
            val role: UserRole,
            var status: UserAccount.AccountStatus
        )
    }
}
