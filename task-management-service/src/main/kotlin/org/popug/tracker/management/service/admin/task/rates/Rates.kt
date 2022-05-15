package org.popug.tracker.management.service.admin.task.rates

object Rates {

    fun credit() = (-20..-10).random()

    fun debit() = (20..40).random()
}
