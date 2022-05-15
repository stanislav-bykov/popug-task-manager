package org.popug.tracker.user.dal.repository.user

import org.popug.tracker.user.dal.model.user.UserAccount.AccountStatus
import org.popug.tracker.user.dal.model.user.UserAccount.AccountStatus.READY_TO_CONFIGURE
import org.popug.tracker.user.dal.model.user.UserExternalAccountCfgTask
import org.popug.tracker.user.dal.model.user.UserExternalAccountCfgTask.CfgTaskStep
import org.popug.tracker.user.dal.model.user.UserExternalAccountCfgTask.CfgTaskStep.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserExternalAccountCfgTaskRepository : JpaRepository<UserExternalAccountCfgTask, Long> {

    @Query(
        value = """
        select task
            from UserExternalAccountCfgTask task
        join fetch task.userAccount account
            where (:status is null or account.status = :status)
              and task.cfgStep = :configurationStep
    """
    )
    fun findAllUserExternalAccounts(
        status: AccountStatus? = null,
        configurationStep: CfgTaskStep
    ): Collection<UserExternalAccountCfgTask>

    fun findAllReadyToConfigureAndNotRegistered() =
        findAllUserExternalAccounts(status = READY_TO_CONFIGURE, configurationStep = REGISTRATION)

    fun findAllReadyToConfigureAndNotRoleAssigned() =
        findAllUserExternalAccounts(status = READY_TO_CONFIGURE, configurationStep = ROLE_ASSIGN)

    fun findAllReadyToConfigureAndCredentialNotRefreshed() =
        findAllUserExternalAccounts(status = READY_TO_CONFIGURE, configurationStep = CREDENTIAL_REFRESHING)

    fun findAllConfigured() = findAllUserExternalAccounts(configurationStep = CONFIGURED)
}
