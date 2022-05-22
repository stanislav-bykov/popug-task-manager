package org.popug.tracker.user.service.admin.user.create

import org.popug.tracker.core.business.BusinessService
import org.popug.tracker.user.dal.model.user.UserAccount
import org.popug.tracker.user.dal.model.user.UserExternalAccountCfgTask
import org.popug.tracker.user.dal.repository.user.UserAccountRepository
import org.popug.tracker.user.dal.repository.user.UserExternalAccountCfgTaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Service(
    private val userAccountRepository: UserAccountRepository,
    private val cfgTaskRepository: UserExternalAccountCfgTaskRepository
) : BusinessService<Api.Request, Api.Response> {

    @Transactional
    override fun invoke(request: Api.Request): Api.Response =
        userAccountRepository.save(request.toUserAccount())
            .also { userAccount -> cfgTaskRepository.save(UserExternalAccountCfgTask(userAccount = userAccount)) }
            .toResponse()

    private companion object {

        fun Api.Request.toUserAccount() = with(body) {
            UserAccount(
                username = username,
                firstName = firstName,
                lastName = lastName,
                email = email,
                role = role
            )
        }

        private fun UserAccount.toResponse() =
            Api.Response(id = id)
    }
}
