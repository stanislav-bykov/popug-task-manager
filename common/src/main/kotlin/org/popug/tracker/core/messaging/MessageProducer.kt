package org.popug.tracker.core.messaging

import mu.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder

interface MessageProducer {

    fun send(message: Message, headers: Map<String, String>)

    fun send(message: Message)
}

class DefaultKafkaProducer(
    private val template: KafkaTemplate<String, Any>
) : MessageProducer {

    private val logger = KotlinLogging.logger {}

    fun invoke(message: Message.MessageBody, topic: String, key: String, headers: Map<String, String>) = run {
        logger.debug(
            "try to publish into topic = {} message with key = {}, value = {} and headers = {}",
            topic,
            key,
            message,
            headers
        )

        template.send(
            MessageBuilder.withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.MESSAGE_KEY, key).apply {
                    headers.forEach { (key, value) -> setHeader(key, value) }
                }.build()
        )
    }

    override fun send(message: Message, headers: Map<String, String>): Unit = with(message) {
        invoke(body, metadata.destinationTopic, metadata.key, headers).get()
    }

    override fun send(message: Message): Unit = with(message) {
        invoke(body, metadata.destinationTopic, metadata.key, emptyMap()).get()
    }
}
