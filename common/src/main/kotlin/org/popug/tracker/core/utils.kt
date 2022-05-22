package org.popug.tracker.core

fun String.asJsonResource(): String {
    return this::class.java.classLoader.getResource("$this.json").readText()
}
