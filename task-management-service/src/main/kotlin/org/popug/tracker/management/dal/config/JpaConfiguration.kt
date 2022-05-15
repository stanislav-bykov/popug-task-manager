package org.popug.tracker.management.dal.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

private const val BASE_USER_DAL_PACKAGE = "org.popug.tracker.management.dal"

@EnableTransactionManagement
@EntityScan(basePackages = ["$BASE_USER_DAL_PACKAGE.model"])
@EnableJpaRepositories(basePackages = ["$BASE_USER_DAL_PACKAGE.repository"])
@Configuration
class JpaConfiguration
