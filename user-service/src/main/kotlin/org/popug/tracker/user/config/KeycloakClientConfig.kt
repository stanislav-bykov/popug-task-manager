package org.popug.tracker.user.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KeycloakClientConfig {

    @Bean
    fun keycloakClientConfig() {
        org.keycloak..admin.client.
    }

}