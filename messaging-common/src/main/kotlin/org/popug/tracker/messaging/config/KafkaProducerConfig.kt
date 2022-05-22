package org.popug.tracker.messaging.config

import mu.KLogging
import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.common.serialization.StringSerializer
import org.popug.tracker.messaging.DefaultKafkaProducer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaProducerConfig {

    @Bean
    fun producerFactory(kafkaProperties: KafkaProperties): ProducerFactory<String, Any> {
        logger.info(">>>my bootstrapAddress {}", kafkaProperties.bootstrapServers)
        val configProps = HashMap<String, Any>().also {
            it[BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.bootstrapServers
            it[ACKS_CONFIG] = "all"
            it[RETRIES_CONFIG] = 10
            it[KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
            it[VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        }
        return DefaultKafkaProducerFactory(kafkaProperties.properties + configProps)
    }

    @Bean
    fun kafkaTemplate(producerFactory: ProducerFactory<String, Any>): KafkaTemplate<String, Any> {
        return KafkaTemplate(producerFactory)
    }

    @Bean
    fun kafkaProducer(template: KafkaTemplate<String, Any>): DefaultKafkaProducer =
        DefaultKafkaProducer(template)

    companion object : KLogging()
}
