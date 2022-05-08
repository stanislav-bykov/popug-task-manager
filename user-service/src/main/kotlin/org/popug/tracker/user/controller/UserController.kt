package org.popug.tracker.user.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed

@RestController
@RequestMapping("/api/users")
class UserController {

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping
    fun getAllUsers() = listOf("first_user", "second_user", "third_user")

}