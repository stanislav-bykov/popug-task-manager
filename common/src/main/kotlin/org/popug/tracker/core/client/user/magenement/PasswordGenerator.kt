package org.popug.tracker.core.client.user.magenement

import java.util.*

object PasswordGenerator {

    fun new(): String = UUID.randomUUID().toString()
}
