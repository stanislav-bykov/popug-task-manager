package org.popug.tracker.core.client.userManagement

import org.popug.tracker.core.client.userManagement.RealmRole.UserRoleValue.ADMIN_ROLE_VALUE
import org.popug.tracker.core.client.userManagement.RealmRole.UserRoleValue.USER_ROLE_VALUE

enum class RealmRole(val value: String) {
    ADMIN_ROLE(ADMIN_ROLE_VALUE), USER_ROLE(USER_ROLE_VALUE);

    object UserRoleValue {
        const val ADMIN_ROLE_VALUE = "APP-ADMIN"
        const val USER_ROLE_VALUE = "APP-USER"
    }
}
