package org.popug.tracker.notification.service.notify.task.assigned

import org.popug.tracker.core.business.BusinessService
import org.springframework.stereotype.Service

@Service
class Service : BusinessService<Api.Request, Unit> {

    override fun invoke(request: Api.Request) {
        // TODO: 14.05.2022 send notification to user
    }
}
