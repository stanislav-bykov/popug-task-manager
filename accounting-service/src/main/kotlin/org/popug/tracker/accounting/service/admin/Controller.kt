package org.popug.tracker.accounting.service.admin

import org.popug.tracker.core.client.user.magenement.ClientRole.ClientRoleValue.CLIENT_ADMIN_ROLE_VALUE
import org.popug.tracker.core.web.JSON
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed
import org.popug.tracker.accounting.service.admin.profit.Service as ProfitService

@RestController
@RequestMapping("/api/admin")
class Controller(
    private val profitService: ProfitService,
) {

    @RolesAllowed(CLIENT_ADMIN_ROLE_VALUE)
    @GetMapping("/profit", produces = [JSON])
    fun profit() = profitService.invoke(Unit)

    @RolesAllowed(CLIENT_ADMIN_ROLE_VALUE)
    @GetMapping("/statistics", produces = [JSON])
    fun statistics() = Unit
}
