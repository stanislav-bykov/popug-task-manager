package org.popug.tracker.user.service.admin.user.configure

import org.popug.tracker.core.business.UnitBusinessService
import org.popug.tracker.user.dal.model.user.UserAccount.AccountStatus.CONFIGURED
import org.popug.tracker.user.dal.model.user.UserExternalAccountCfgTask
import org.popug.tracker.user.dal.repository.user.UserExternalAccountCfgTaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRES_NEW
import org.springframework.transaction.annotation.Transactional

@Service
class Service(
    private val cfgTaskRepository: UserExternalAccountCfgTaskRepository
) : UnitBusinessService {

    @Transactional
    override fun invoke(request: Unit) {
        cfgTaskRepository.findAllConfigured()
            .forEach { task -> configure(task) }
    }

    @Transactional(propagation = REQUIRES_NEW)
    fun configure(task: UserExternalAccountCfgTask) {
        task.userAccount.status = CONFIGURED
        // TODO: 14.05.2022 soft deletion
        cfgTaskRepository.delete(task)
    }
}
