package org.popug.tracker.user.service.admin.user

import org.popug.tracker.core.client.user.magenement.ClientRole.ClientRoleValue.CLIENT_ADMIN_ROLE_VALUE
import org.popug.tracker.core.web.JSON
import org.springframework.web.bind.annotation.*
import javax.annotation.security.RolesAllowed
import org.popug.tracker.user.service.admin.user.account.getList.Service as GetUserAccountService
import org.popug.tracker.user.service.admin.user.create.Api as CreateUserApi
import org.popug.tracker.user.service.admin.user.create.Service as CreateUserService
import org.popug.tracker.user.service.admin.user.getList.Service as GetListService
import org.popug.tracker.user.service.admin.user.update.Api as UpdateUserApi
import org.popug.tracker.user.service.admin.user.update.Service as UpdateUserService

@RestController
@RequestMapping("/api/users")
class Controller(
    private val getListService: GetListService,
    private val getUserAccountService: GetUserAccountService,
    private val createUserService: CreateUserService,
    private val updateUserService: UpdateUserService,
) {

    @RolesAllowed(CLIENT_ADMIN_ROLE_VALUE)
    @GetMapping(produces = [JSON])
    fun getAllUsers() = getListService.invoke(Unit)

    @RolesAllowed(CLIENT_ADMIN_ROLE_VALUE)
    @PutMapping(consumes = [JSON], produces = [JSON])
    fun registerUser(@RequestBody body: CreateUserApi.RequestBody) =
        createUserService.invoke(CreateUserApi.Request(body = body))

    @RolesAllowed(CLIENT_ADMIN_ROLE_VALUE)
    @PutMapping("/{id}", consumes = [JSON], produces = [JSON])
    fun updateUser(@PathVariable id: String, @RequestBody body: UpdateUserApi.RequestBody) =
        updateUserService.invoke(UpdateUserApi.Request(id = id, body = body))

    @RolesAllowed(CLIENT_ADMIN_ROLE_VALUE)
    @DeleteMapping("/{id}", consumes = [JSON])
    fun deleteUser(@PathVariable id: String) = NotImplementedError()

    @RolesAllowed(CLIENT_ADMIN_ROLE_VALUE)
    @GetMapping("/accounts", produces = [JSON])
    fun getAllUserAccounts() = getUserAccountService.invoke(Unit)
}
