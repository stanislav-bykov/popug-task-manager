package org.popug.tracker.management.dal.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.time.OffsetDateTime
import java.util.*

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "offsetDateTimeProvider")
class JpaAuditingConfiguration {

    @Bean
    fun offsetDateTimeProvider() = DateTimeProvider { Optional.of(OffsetDateTime.now()) }
}
