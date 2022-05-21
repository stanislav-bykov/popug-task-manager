package org.popug.tracker.core.messaging.business

import org.popug.tracker.core.messaging.Message

interface BusinessEvent<T : BusinessEventPayload> : Message {
    override val payload: T
}

interface BusinessEventPayload : Message.MessagePayload
