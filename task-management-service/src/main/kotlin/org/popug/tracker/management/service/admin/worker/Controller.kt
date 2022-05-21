package org.popug.tracker.management.service.admin.worker

import org.popug.tracker.core.client.user.magenement.ClientRole.ClientRoleValue.CLIENT_ADMIN_ROLE_VALUE
import org.popug.tracker.core.web.JSON
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed
import org.popug.tracker.management.service.admin.worker.getList.Service as GetListService

@RestController
@RequestMapping("/api/admin/workers")
class Controller(
    private val getListService: GetListService,
) {

    @RolesAllowed(CLIENT_ADMIN_ROLE_VALUE)
    @GetMapping(produces = [JSON])
    fun getList() = getListService.invoke(Unit)
}
