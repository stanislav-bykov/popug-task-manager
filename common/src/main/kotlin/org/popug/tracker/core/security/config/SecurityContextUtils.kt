package org.popug.tracker.core.security.config

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

object SecurityContextUtils {

    fun userIdentifier(): String {
        val authentication = getAuthentication()
        val token = authentication.account.keycloakSecurityContext.token
        return token.subject
    }

    private fun getAuthentication(): KeycloakAuthenticationToken {
        return SecurityContextHolder.getContext().authentication as KeycloakAuthenticationToken
    }
}
