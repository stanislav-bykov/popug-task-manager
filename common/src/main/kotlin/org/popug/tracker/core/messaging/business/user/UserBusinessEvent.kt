package org.popug.tracker.core.messaging.business.user

import org.popug.tracker.core.messaging.Message
import org.popug.tracker.core.messaging.MessageSource.BusinessEvent.Users.USER_REGISTERED
import org.popug.tracker.core.messaging.business.BusinessEvent
import org.popug.tracker.core.messaging.business.BusinessEventBody

data class UserRegisteredBusinessEvent(override val body: UserRegisteredBusinessEventBody) :
    BusinessEvent<UserRegisteredBusinessEventBody> {

    override val metadata: Message.Metadata = Message.Metadata(key = body.publicId, destinationTopic = USER_REGISTERED)
}

data class UserRegisteredBusinessEventBody(
    override val publicId: String,
    val firstName: String,
    val lastName: String,
    val email: String
) : BusinessEventBody
