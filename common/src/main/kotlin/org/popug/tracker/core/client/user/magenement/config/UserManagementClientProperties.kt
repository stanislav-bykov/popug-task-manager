package org.popug.tracker.core.client.user.magenement.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("keycloak")
data class UserManagementClientProperties(
    val authServerUrl: String,
    val realm: String,
    val resource: String,
    val credentials: Credentials
) {
    data class Credentials(
        val secret: String
    )
}
