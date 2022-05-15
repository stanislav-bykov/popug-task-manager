package org.popug.tracker.management.service.admin.task

import org.popug.tracker.core.client.user.magenement.ClientRole.ClientRoleValue.CLIENT_ADMIN_ROLE_VALUE
import org.popug.tracker.core.web.JSON
import org.springframework.web.bind.annotation.*
import javax.annotation.security.RolesAllowed
import org.popug.tracker.management.service.admin.task.create.Api as CreateServiceApi
import org.popug.tracker.management.service.admin.task.create.Service as CreateService
import org.popug.tracker.management.service.admin.task.getList.Service as GetListService

@RestController
@RequestMapping("/api/admin/tasks")
class Controller(
    private val getListService: GetListService,
    private val createService: CreateService,
    // private val shuffleService: ShuffleService,*/
) {

    @RolesAllowed(CLIENT_ADMIN_ROLE_VALUE)
    @GetMapping(produces = [JSON])
    fun getList() = getListService.invoke(Unit)

    @RolesAllowed(CLIENT_ADMIN_ROLE_VALUE)
    @PostMapping(consumes = [JSON], produces = [JSON])
    fun complete(@RequestBody body: CreateServiceApi.RequestBody) =
        createService.invoke(CreateServiceApi.Request(body = body))
}
