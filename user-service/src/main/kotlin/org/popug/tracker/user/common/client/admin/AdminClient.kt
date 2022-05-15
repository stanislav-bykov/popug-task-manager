package org.popug.tracker.user.common.client.admin

interface AdminClient {

    fun getUser(request: Api.GetUserRequest): Api.GetUserResponse

    fun getAllUsers(): Api.GetListUserResponse

    fun createUser(request: Api.CreateUserRequest): Api.CreateUserResponse

    fun updateUser(request: Api.UpdateUserRequest): Api.UpdateUserResponse

    fun grantRealmRole(request: Api.GrantUserRoleRequest)

    fun resetPassword(request: Api.UserUpdatePasswordRequest)
}
