package org.popug.tracker.user.service.admin.user.activate

import org.popug.tracker.core.business.UnitBusinessService
import org.popug.tracker.messaging.MessageProducer
import org.popug.tracker.messaging.business.user.UserRegisteredBusinessEvent
import org.popug.tracker.messaging.business.user.UserRegisteredBusinessEvent.UserRegisteredBusinessEventPayload
import org.popug.tracker.messaging.streaming.user.CreatedUserStreamingMessagePayload
import org.popug.tracker.messaging.streaming.user.UserStreamingMessage
import org.popug.tracker.user.dal.model.user.UserAccount
import org.popug.tracker.user.dal.model.user.UserAccount.AccountStatus.ACTIVE
import org.popug.tracker.user.dal.model.user.UserAccount.AccountStatus.CONFIGURED
import org.popug.tracker.user.dal.repository.user.UserAccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.REQUIRES_NEW
import org.springframework.transaction.annotation.Transactional

@Service
class Service(
    private val userAccountRepository: UserAccountRepository,
    private val messageProducer: MessageProducer,
) : UnitBusinessService {

    @Transactional
    override fun invoke(request: Unit) {
        userAccountRepository.findAllByStatus(CONFIGURED).forEach { userAccount -> activate(userAccount) }
    }

    @Transactional(propagation = REQUIRES_NEW)
    fun activate(userAccount: UserAccount) {
        println(userAccount)
        userAccount.status = ACTIVE
        // TODO: 14.05.2022 atomicity
        messageProducer.send(userAccount.toStreamingMessage())
        messageProducer.send(userAccount.toBusinessEvent())
    }

    private companion object {
        fun UserAccount.toStreamingMessage() = UserStreamingMessage(
            CreatedUserStreamingMessagePayload(
                publicId = publicId, firstName = firstName, lastName = lastName, role = role, email = email
            )
        )

        fun UserAccount.toBusinessEvent() = UserRegisteredBusinessEvent(
            UserRegisteredBusinessEventPayload(
                userPublicId = publicId, firstName = firstName, lastName = lastName, email = email
            )
        )
    }
}
