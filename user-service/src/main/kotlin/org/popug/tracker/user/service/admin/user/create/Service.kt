package org.popug.tracker.user.service.admin.user.create

import org.popug.tracker.core.business.BusinessService
import org.popug.tracker.core.client.userManagement.RealmRole
import org.popug.tracker.user.common.client.admin.AdminClient
import org.popug.tracker.user.common.client.admin.Api.CreateUserRequest
import org.popug.tracker.user.common.client.admin.Api.CreateUserResponse
import org.popug.tracker.user.common.client.admin.Api.GrantUserRoleRequest
import org.popug.tracker.user.common.client.admin.Api.UserUpdatePasswordRequest
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class Service(
    private val adminClient: AdminClient
) : BusinessService<Api.Request, Api.Response> {

    override fun invoke(request: Api.Request): Api.Response =
        adminClient.createUser(request.toApiRequest())
            .also {
                adminClient.grantRealmRole(GrantUserRoleRequest(id = it.id, userRole = RealmRole.USER_ROLE))
                adminClient.resetPassword(UserUpdatePasswordRequest(id = it.id, UUID.randomUUID().toString()))
            }.run { toResponse(this) }

    private fun toResponse(user: CreateUserResponse) = user.toResponse()

    private companion object {
        fun Api.Request.toApiRequest(): CreateUserRequest =
            CreateUserRequest(
                username = username,
                firstName = firstName,
                lastName = lastName,
                email = email
            )

        private fun CreateUserResponse.toResponse() =
            Api.Response(id = id)
    }
}
