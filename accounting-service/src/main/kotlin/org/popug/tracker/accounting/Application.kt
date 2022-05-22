package org.popug.tracker.accounting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
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
