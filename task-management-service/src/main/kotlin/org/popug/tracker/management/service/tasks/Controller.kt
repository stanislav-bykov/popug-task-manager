package org.popug.tracker.management.service.tasks

import org.popug.tracker.core.client.user.magenement.ClientRole.ClientRoleValue.CLIENT_USER_ROLE_VALUE
import org.popug.tracker.core.security.config.SecurityContextUtils
import org.popug.tracker.core.web.JSON
import org.springframework.web.bind.annotation.*
import javax.annotation.security.RolesAllowed
import org.popug.tracker.management.service.tasks.complete.Api as CompleteApiRequest
import org.popug.tracker.management.service.tasks.complete.Service as CompleteService
import org.popug.tracker.management.service.tasks.getList.Api as GetListApiRequest
import org.popug.tracker.management.service.tasks.getList.Service as GetListService

@RestController
@RequestMapping("/api/tasks")
class Controller(
    private val getListService: GetListService,
    private val completeService: CompleteService,
) {

    @RolesAllowed(CLIENT_USER_ROLE_VALUE)
    @GetMapping(produces = [JSON])
    fun getList() = getListService.invoke(
        GetListApiRequest.Request(
            userPublicId = SecurityContextUtils.userIdentifier()
        )
    )

    @RolesAllowed(CLIENT_USER_ROLE_VALUE)
    @PutMapping("/{taskId}", produces = [JSON])
    fun complete(@PathVariable taskId: Long) = completeService.invoke(
        CompleteApiRequest.Request(
            userPublicId = SecurityContextUtils.userIdentifier(),
            taskId = taskId
        )
    )
}
