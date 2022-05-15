package org.popug.tracker.user.service.admin.user.roleAssign

import org.popug.tracker.core.business.UnitBusinessService
import org.popug.tracker.core.client.user.magenement.RealmRole
import org.popug.tracker.core.dal.common.model.user.UserRole
import org.popug.tracker.user.common.client.admin.AdminClient
import org.popug.tracker.user.common.client.admin.Api.GrantUserRoleRequest
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
        cfgTaskRepository.findAllReadyToConfigureAndNotRoleAssigned()
            .forEach { task -> grantRealmRole(task) }
    }

    @Transactional(propagation = REQUIRES_NEW)
    fun grantRealmRole(task: UserExternalAccountCfgTask) {
        // TODO: 14.05.2022 handle case when grant task completed and transaction rollback
        task.also {
            adminClient.grantRealmRole(task.toApiRequest(it.userAccount))
            task.completeCfxStep()
        }
    }

    private companion object {

        fun UserExternalAccountCfgTask.toApiRequest(userAccount: UserAccount) = with(userAccount) {
            GrantUserRoleRequest(
                id = publicId,
                userRole = when (role) {
                    UserRole.ADMIN -> RealmRole.REALM_ADMIN_ROLE
                    UserRole.USER -> RealmRole.REALM_USER_ROLE
                }
            )
        }
    }
}
