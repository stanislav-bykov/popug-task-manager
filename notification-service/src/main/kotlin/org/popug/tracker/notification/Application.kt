package org.popug.tracker.notification

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator

@ConfigurationPropertiesScan(
    basePackages = ["org.popug.tracker"]
)
@SpringBootApplication(
    scanBasePackages = ["org.popug.tracker"],
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator::class
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
