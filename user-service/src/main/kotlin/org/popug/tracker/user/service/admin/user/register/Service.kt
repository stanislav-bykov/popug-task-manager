package org.popug.tracker.user.service.admin.user.register

import org.popug.tracker.core.business.UnitBusinessService
import org.popug.tracker.user.common.client.admin.AdminClient
import org.popug.tracker.user.common.client.admin.Api.CreateUserRequest
import org.popug.tracker.user.dal.model.user.UserAccount
import org.popug.tracker.user.dal.model.user.UserExternalAccountCfgTask
import org.popug.tracker.user.dal.repository.user.UserAccountRepository
import org.popug.tracker.user.dal.repository.user.UserExternalAccountCfgTaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRES_NEW
import org.springframework.transaction.annotation.Transactional

@Service
class Service(
    private val adminClient: AdminClient,
    private val userAccountRepository: UserAccountRepository,
    private val cfgTaskRepository: UserExternalAccountCfgTaskRepository
) : UnitBusinessService {

    @Transactional
    override fun invoke(request: Unit) {
        cfgTaskRepository.findAllReadyToConfigureAndNotRegistered()
            .forEach { task -> registerUser(task) }
    }

    @Transactional(propagation = REQUIRES_NEW)
    fun registerUser(task: UserExternalAccountCfgTask) {
        // TODO: 14.05.2022 handle case when user was created and transaction rollback
        with(task.userAccount) {
            adminClient.createUser(this.toApiRequest())
                .also {
                    userAccountRepository.modifyPublicId(userId = id, publicId = it.id)
                    task.completeCfxStep()
                }
        }
    }

    private companion object {

        fun UserAccount.toApiRequest() =
            CreateUserRequest(
                username = username,
                firstName = firstName,
                lastName = lastName,
                email = email
            )
    }
}
