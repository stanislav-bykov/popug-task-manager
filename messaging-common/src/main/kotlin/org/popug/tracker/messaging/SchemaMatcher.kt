package org.popug.tracker.messaging

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.everit.json.schema.loader.SchemaLoader
import org.json.JSONObject
import org.popug.tracker.core.asJsonResource
import org.popug.tracker.messaging.Message.MessageHeader
import java.util.*

object SchemaMatcher {

    fun <K, V> match(record: ConsumerRecord<K, V>) {
        val headers = record.headers()
        val payload = record.value()

        val resource = payload!!::class.java.classLoader
            .getResourceAsStream("schema/task/streaming/v1.json")

        Optional.ofNullable(
            headers.headers(MessageHeader.SCHEMA_SOURCE).firstOrNull()
        ).ifPresent {
            val schema = SchemaLoader.load(JSONObject(it.value().toString().asJsonResource()))
            schema.validate(JSONObject(payload))
        }
    }
}
