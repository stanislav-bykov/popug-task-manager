package org.popug.tracker.user.service.admin.user.resetPassword

import org.popug.tracker.core.business.UnitBusinessService
import org.popug.tracker.core.client.user.magenement.PasswordGenerator
import org.popug.tracker.user.common.client.admin.AdminClient
import org.popug.tracker.user.common.client.admin.Api.UserUpdatePasswordRequest
import org.popug.tracker.user.dal.model.user.UserAccount
import org.popug.tracker.user.dal.model.user.UserExternalAccountCfgTask
import org.popug.tracker.user.dal.repository.user.UserExternalAccountCfgTaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRES_NEW
import org.springframework.transaction.annotation.Transactional

@Service
class Service(
    private val adminClient: AdminClient,
    private val cfgTaskRepository: UserExternalAccountCfgTaskRepository
) : UnitBusinessService {

    @Transactional
    override fun invoke(request: Unit) {
        cfgTaskRepository.findAllReadyToConfigureAndCredentialNotRefreshed()
            .forEach { task -> resetPassword(task) }
    }

    @Transactional(propagation = REQUIRES_NEW)
    fun resetPassword(task: UserExternalAccountCfgTask) {
        // TODO: 14.05.2022 handle case when password refreshed and transaction rollback
        task.also {
            adminClient.resetPassword(toApiRequest(it.userAccount))
            it.completeCfxStep()
        }
    }

    private companion object {

        fun toApiRequest(userAccount: UserAccount) = with(userAccount) {
            UserUpdatePasswordRequest(
                id = publicId,
                password = PasswordGenerator.new()
            )
        }
    }
}
