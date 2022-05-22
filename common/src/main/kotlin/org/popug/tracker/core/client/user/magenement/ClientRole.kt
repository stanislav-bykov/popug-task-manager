package org.popug.tracker.core.client.user.magenement

import org.popug.tracker.core.client.user.magenement.ClientRole.ClientRoleValue.CLIENT_ADMIN_ROLE_VALUE
import org.popug.tracker.core.client.user.magenement.ClientRole.ClientRoleValue.CLIENT_USER_ROLE_VALUE

enum class ClientRole(val value: String) {
    CLIENT_ADMIN_ROLE(CLIENT_ADMIN_ROLE_VALUE), CLIENT_USER_ROLE(CLIENT_USER_ROLE_VALUE);

    object ClientRoleValue {
        const val CLIENT_ADMIN_ROLE_VALUE = "ADMIN"
        const val CLIENT_USER_ROLE_VALUE = "USER"
    }
}
