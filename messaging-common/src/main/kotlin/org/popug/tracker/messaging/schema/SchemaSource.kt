package org.popug.tracker.messaging.schema

import java.lang.annotation.Inherited

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
annotation class SchemaSource(
    val source: String
)

object SchemaSourceVersion {

    object Streaming {
        object TaskStreaming {
            object Source {
                private const val BASE = "/task/streaming"
                const val V1 = "$BASE/v1"
                const val V2 = "$BASE/v2"
            }
        }
    }
}
