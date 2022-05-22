package org.popug.tracker.messaging

import mu.KotlinLogging
import org.popug.tracker.messaging.schema.SchemaSource
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.messaging.support.MessageHeaderAccessor

interface MessageProducer {

    fun send(message: Message, headers: Map<String, String>)

    fun send(message: Message)
}

class DefaultKafkaProducer(
    private val template: KafkaTemplate<String, Any>
) : MessageProducer {

    private val logger = KotlinLogging.logger {}

    fun invoke(payload: Message.MessagePayload, topic: String, key: String, headers: Map<String, String>) = run {

        headers.entries
        val headerAccessor = MessageHeaderAccessor()
            .apply {
                setHeader(KafkaHeaders.TOPIC, topic)
                setHeader(KafkaHeaders.MESSAGE_KEY, key)
            }

        with(payload.javaClass) {
            if (isAnnotationPresent(SchemaSource::class.java)) {
                val schemaSource = getAnnotation(SchemaSource::class.java)
                headerAccessor.setHeader("SCHEMA_SOURCE", schemaSource.source)
            }
        }

        logger.debug(
            "try to publish into topic = {} message with key = {}, value = {} and headers = {}",
            topic,
            key,
            payload,
            headerAccessor
        )

        template.send(
            MessageBuilder.withPayload(payload)
                .setHeaders(headerAccessor)
                .build()
        )
    }

    override fun send(message: Message, headers: Map<String, String>): Unit = with(message) {
        invoke(payload, metadata.destinationTopic, metadata.key, headers).get()
    }

    override fun send(message: Message): Unit = with(message) {
        invoke(payload, metadata.destinationTopic, metadata.key, emptyMap()).get()
    }
}
