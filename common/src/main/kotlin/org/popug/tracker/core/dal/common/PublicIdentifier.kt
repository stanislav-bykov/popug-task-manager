package org.popug.tracker.core.dal.common

import java.util.UUID

object PublicIdentifier {
    fun new(): String = UUID.randomUUID().toString()
}
