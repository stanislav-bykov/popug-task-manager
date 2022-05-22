package org.popug.tracker.user.service.admin.user.account.getList

import org.popug.tracker.core.business.BusinessService
import org.popug.tracker.user.dal.model.user.UserAccount
import org.popug.tracker.user.dal.repository.user.UserAccountRepository
import org.springframework.stereotype.Service

@Service
class Service(
    private val userAccountRepository: UserAccountRepository
) : BusinessService<Unit, Api.Response> {

    override fun invoke(request: Unit) = toResponse(userAccountRepository.findAll())

    private fun toResponse(response: MutableList<UserAccount>) =
        response.map { it.toResponse() }.run { Api.Response(this) }

    companion object {
        private fun UserAccount.toResponse() =
            Api.Response.Data(
                id = id,
                publicId = publicId,
                username = username,
                firstName = firstName,
                lastName = lastName,
                email = email,
                role = role,
                status = status
            )
    }
}
