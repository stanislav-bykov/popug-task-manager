package org.popug.tracker.core.messaging.config

import mu.KLogging
import org.apache.kafka.clients.consumer.ConsumerConfig.*
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class KafkaConsumerConfig {

    @Bean
    fun consumerFactory(kafkaProperties: KafkaProperties): ConsumerFactory<String, Any> {
        with(kafkaProperties.consumer) {
            logger.info(">>>my bootstrapAddress {}, {}", bootstrapServers, groupId)
        }
        val configProps = HashMap<String, Any>().also {
            it[BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.bootstrapServers
            it[KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
            it[VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
            it[AUTO_OFFSET_RESET_CONFIG] = "earliest"
            it[JsonDeserializer.TRUSTED_PACKAGES] = "*"
        }

        return DefaultKafkaConsumerFactory(kafkaProperties.properties + configProps)
    }

    @Bean
    fun kafkaListenerContainerFactory(consumerFactory: ConsumerFactory<String, Any>): ConcurrentKafkaListenerContainerFactory<String, Any> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Any>()
        factory.consumerFactory = consumerFactory
        return factory
    }

    companion object : KLogging()
}
