package org.popug.tracker.user.common.client.admin

import org.popug.tracker.core.client.userManagement.RealmRole

object Api {

    data class GetListUserResponse(
        val values: Collection<UserResponseBody>
    )

    data class CreateUserRequest(
        val username: String,
        val firstName: String,
        val lastName: String,
        val email: String
    )

    data class CreateUserResponse(
        val id: String
    )

    data class UpdateUserRequest(
        val id: String,
        val firstName: String?,
        val lastName: String?,
        val email: String?
    )

    data class UpdateUserResponse(
        val id: String,
        val firstName: String,
        val lastName: String,
        val email: String
    )

    data class GetUserRequest(val id: String)

    data class GetUserResponse(
        val body: UserResponseBody
    )

    data class UserResponseBody(
        val id: String,
        val username: String,
        val firstName: String?,
        val lastName: String?,
        val email: String?,
    )

    data class GrantUserRoleRequest(
        val id: String,
        val userRole: RealmRole
    )

    data class UserUpdatePasswordRequest(
        val id: String,
        val password: String
    )
}
