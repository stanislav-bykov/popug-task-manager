package org.popug.tracker.management.streaming.service.user.create

import org.popug.tracker.core.dal.common.model.user.UserRole

object Api {

    data class Request(
        val publicId: String,
        val firstName: String,
        val lastName: String,
        val role: UserRole
    )
}
