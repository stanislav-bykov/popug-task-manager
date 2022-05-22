package org.popug.tracker.accounting.service.account

import org.popug.tracker.accounting.service.account.balance.Api
import org.popug.tracker.core.client.user.magenement.ClientRole.ClientRoleValue.CLIENT_USER_ROLE_VALUE
import org.popug.tracker.core.security.config.SecurityContextUtils
import org.popug.tracker.core.web.JSON
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed
import org.popug.tracker.accounting.service.account.balance.Service as BalanceService

@RestController
@RequestMapping("/api/account")
class Controller(
    private val balanceService: BalanceService,
) {

    @RolesAllowed(CLIENT_USER_ROLE_VALUE)
    @GetMapping("/balance", produces = [JSON])
    fun profit() = balanceService.invoke(
        Api.Request(userPublicId = SecurityContextUtils.userIdentifier())
    )
}
