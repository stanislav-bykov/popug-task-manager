package org.popug.tracker.user.service.admin.user.update

import org.popug.tracker.core.business.BusinessService
import org.popug.tracker.user.common.client.admin.AdminClient
import org.popug.tracker.user.common.client.admin.Api.UpdateUserRequest
import org.popug.tracker.user.common.client.admin.Api.UpdateUserResponse
import org.springframework.stereotype.Service

@Service
class Service(
    private val adminClient: AdminClient
) : BusinessService<Api.Request, Api.Response> {

    override fun invoke(request: Api.Request): Api.Response =
        toResponse(adminClient.updateUser(request.toApiRequest()))

    private fun toResponse(user: UpdateUserResponse) = user.toResponse()

    private companion object {
        fun Api.Request.toApiRequest(): UpdateUserRequest = with(body) {
            UpdateUserRequest(
                id = id,
                firstName = firstName,
                lastName = lastName,
                email = lastName
            )
        }

        private fun UpdateUserResponse.toResponse() =
            Api.Response(
                id = id,
                firstName = firstName,
                lastName = lastName,
                email = lastName
            )
    }
}
