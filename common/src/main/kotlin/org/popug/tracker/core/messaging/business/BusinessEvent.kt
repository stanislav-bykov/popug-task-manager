package org.popug.tracker.core.messaging.business

import org.popug.tracker.core.messaging.Message

interface BusinessEvent<T : BusinessEventBody> : Message {
    override val body: T
}

interface BusinessEventBody : Message.MessageBody
