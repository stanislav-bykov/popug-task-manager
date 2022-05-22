package org.popug.tracker.core.client.user.magenement.config

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder
import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.admin.client.resource.RealmResource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserManagementClientConfig(
    private val properties: UserManagementClientProperties
) {

    @Bean
    fun userManagementClient(): Keycloak = with(properties) {
        KeycloakBuilder.builder()
            .serverUrl(authServerUrl)
            .realm(realm)
            .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
            .clientId(resource)
            .clientSecret(credentials.secret)
            .resteasyClient(
                ResteasyClientBuilder().connectionPoolSize(10).build()
            )
            .build()
    }

    @Bean
    fun realmResource(keycloak: Keycloak): RealmResource {
        return keycloak.realm(properties.realm)
    }
}
