package org.popug.tracker.user.service.admin.user

import org.popug.tracker.core.client.userManagement.ClientRole.ADMIN_ROLE
import org.popug.tracker.core.web.JSON
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed
import org.popug.tracker.user.service.admin.user.create.Api as CreateUserApi
import org.popug.tracker.user.service.admin.user.create.Service as CreateUserService
import org.popug.tracker.user.service.admin.user.getList.Service as GetListService
import org.popug.tracker.user.service.admin.user.update.Api as UpdateUserApi
import org.popug.tracker.user.service.admin.user.update.Service as UpdateUserService

@RestController
@RequestMapping("/api/users")
class Controller(
    private val getListService: GetListService,
    private val createUserService: CreateUserService,
    private val updateUserService: UpdateUserService,
) {

    @RolesAllowed(ADMIN_ROLE)
    @GetMapping(produces = [JSON])
    fun getAllUsers() = getListService.invoke(Unit)

    @RolesAllowed(ADMIN_ROLE)
    @PutMapping(consumes = [JSON], produces = [JSON])
    fun registerUser(@RequestBody request: CreateUserApi.Request) = createUserService.invoke(request)

    @RolesAllowed(ADMIN_ROLE)
    @PutMapping("/{id}", consumes = [JSON], produces = [JSON])
    fun updateUser(@PathVariable id: String, @RequestBody body: UpdateUserApi.RequestBody) =
        updateUserService.invoke(UpdateUserApi.Request(id = id, body = body))
}
