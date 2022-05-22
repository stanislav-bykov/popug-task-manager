package org.popug.tracker.messaging.business

import org.popug.tracker.messaging.Message

interface BusinessEvent<T : BusinessEventPayload> : Message {
    override val payload: T
}

interface BusinessEventPayload : Message.MessagePayload
