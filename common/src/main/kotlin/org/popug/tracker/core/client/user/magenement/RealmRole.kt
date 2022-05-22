package org.popug.tracker.core.client.user.magenement

import org.popug.tracker.core.client.user.magenement.RealmRole.RealmRoleValue.REALM_ADMIN_ROLE_VALUE
import org.popug.tracker.core.client.user.magenement.RealmRole.RealmRoleValue.REALM_USER_ROLE_VALUE

enum class RealmRole(val value: String) {
    REALM_ADMIN_ROLE(REALM_ADMIN_ROLE_VALUE), REALM_USER_ROLE(REALM_USER_ROLE_VALUE);

    object RealmRoleValue {
        const val REALM_ADMIN_ROLE_VALUE = "APP-ADMIN"
        const val REALM_USER_ROLE_VALUE = "APP-USER"
    }
}
