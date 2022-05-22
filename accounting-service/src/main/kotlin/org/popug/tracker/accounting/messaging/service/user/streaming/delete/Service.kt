package org.popug.tracker.accounting.messaging.service.user.streaming.delete

import org.popug.tracker.accounting.dal.repository.account.AccountRepository
import org.popug.tracker.core.business.BusinessService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Service(
    private val accountRepository: AccountRepository
) : BusinessService<Api.Request, Unit> {

    @Transactional
    override fun invoke(request: Api.Request) {
        // TODO: 20.05.2022 delete
        accountRepository.findByPublicId(request.publicId)
            .map {}
    }

    private companion object
}
