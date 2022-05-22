package org.popug.tracker.management.service.admin.task

import org.popug.tracker.core.client.user.magenement.ClientRole.ClientRoleValue.CLIENT_ADMIN_ROLE_VALUE
import org.popug.tracker.core.web.JSON
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed
import org.popug.tracker.management.service.admin.task.getList.Service as GetListService
import org.popug.tracker.management.service.admin.task.shuffle.Service as ShuffleService

@RestController
@RequestMapping("/api/admin/tasks")
class Controller(
    private val getListService: GetListService,
    private val shuffleService: ShuffleService
) {

    @RolesAllowed(CLIENT_ADMIN_ROLE_VALUE)
    @GetMapping(produces = [JSON])
    fun getList() = getListService.invoke(Unit)

    @RolesAllowed(CLIENT_ADMIN_ROLE_VALUE)
    @PostMapping("/shuffle")
    fun shuffle() = shuffleService.invoke(Unit)
}
