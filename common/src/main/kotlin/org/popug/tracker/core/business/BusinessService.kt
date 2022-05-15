package org.popug.tracker.core.business

interface BusinessService<in Request, out Response> {

    fun invoke(request: Request): Response
}
