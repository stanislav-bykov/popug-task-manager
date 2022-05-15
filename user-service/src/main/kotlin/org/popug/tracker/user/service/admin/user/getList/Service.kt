package org.popug.tracker.user.service.admin.user.getList

import org.popug.tracker.core.business.BusinessService
import org.popug.tracker.user.common.client.admin.AdminClient
import org.popug.tracker.user.common.client.admin.Api.GetListUserResponse
import org.popug.tracker.user.common.client.admin.Api.UserResponseBody
import org.springframework.stereotype.Service

@Service
class Service(
    private val adminClient: AdminClient
) : BusinessService<Unit, Api.Response> {

    override fun invoke(request: Unit) = toResponse(adminClient.getAllUsers())

    private fun toResponse(response: GetListUserResponse) =
        response.values.map { it.toResponse() }.run { Api.Response(this) }

    companion object {
        private fun UserResponseBody.toResponse() =
            Api.Response.Data(
                id = id,
                firstName = firstName,
                lastName = lastName,
                email = email
            )
    }
}
