package org.popug.tracker.management.service.tasks

import org.popug.tracker.core.client.user.magenement.ClientRole.ClientRoleValue.CLIENT_ADMIN_ROLE_VALUE
import org.popug.tracker.core.client.user.magenement.ClientRole.ClientRoleValue.CLIENT_USER_ROLE_VALUE
import org.popug.tracker.core.security.config.SecurityContextUtils
import org.popug.tracker.core.web.JSON
import org.springframework.web.bind.annotation.*
import javax.annotation.security.RolesAllowed
import org.popug.tracker.management.service.tasks.complete.Api as CompleteApiRequest
import org.popug.tracker.management.service.tasks.complete.Service as CompleteService
import org.popug.tracker.management.service.tasks.create.Api as CreateServiceApi
import org.popug.tracker.management.service.tasks.create.Service as CreateService
import org.popug.tracker.management.service.tasks.getList.Api as GetListApiRequest
import org.popug.tracker.management.service.tasks.getList.Service as GetListService

@RestController
@RequestMapping("/api/tasks")
class Controller(
    private val getListService: GetListService,
    private val createService: CreateService,
    private val completeService: CompleteService,
) {

    @RolesAllowed(CLIENT_USER_ROLE_VALUE)
    @GetMapping(produces = [JSON])
    fun getList() = getListService.invoke(
        GetListApiRequest.Request(
            userPublicId = SecurityContextUtils.userIdentifier()
        )
    )

    @RolesAllowed(CLIENT_ADMIN_ROLE_VALUE, CLIENT_USER_ROLE_VALUE)
    @PostMapping(consumes = [JSON], produces = [JSON])
    fun create(@RequestBody body: CreateServiceApi.RequestBody) =
        createService.invoke(CreateServiceApi.Request(body = body))

    @RolesAllowed(CLIENT_USER_ROLE_VALUE)
    @PutMapping("/{taskId}/complete", produces = [JSON])
    fun complete(@PathVariable taskId: Long) = completeService.invoke(
        CompleteApiRequest.Request(
            userPublicId = SecurityContextUtils.userIdentifier(),
            taskId = taskId
        )
    )
}
