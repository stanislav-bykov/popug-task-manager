package org.popug.tracker.user.service.admin.user.client

import org.keycloak.admin.client.CreatedResponseUtil
import org.keycloak.admin.client.resource.RealmResource
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.popug.tracker.user.common.client.admin.AdminClient
import org.popug.tracker.user.common.client.admin.Api
import org.springframework.stereotype.Component
import javax.ws.rs.core.Response

@Component
class KeycloakAdminClient(
    private val realmResource: RealmResource,
) : AdminClient {

    override fun getUser(request: Api.GetUserRequest): Api.GetUserResponse =
        getUserResource(request.id)
            .toRepresentation()
            .toUserResponseBody()
            .run { Api.GetUserResponse(this) }

    override fun getAllUsers() = Api.GetListUserResponse(realmResource.users().list().map { it.toUserResponseBody() })

    override fun createUser(request: Api.CreateUserRequest): Api.CreateUserResponse {
        val usersResource = realmResource.users()
        val response = usersResource.create(request.toUserRepresentation())
        return Api.CreateUserResponse(extractUserId(response))
    }

    override fun updateUser(request: Api.UpdateUserRequest): Api.UpdateUserResponse {
        val userResource = getUserResource(request.id)
        userResource.update(request.toUserRepresentation())
        return userResource.toRepresentation().toUpdateUserResponse()
    }

    override fun grantRealmRole(request: Api.GrantUserRoleRequest) = with(request) {
        val userResource = getUserResource(id)

        // Get realm role
        val rolesResource = realmResource.roles()
        val realmRoleUser = rolesResource.get(userRole.value).toRepresentation()

        // Assign realm role to user
        userResource.roles().realmLevel().add(listOf(realmRoleUser))
    }

    override fun resetPassword(request: Api.UserUpdatePasswordRequest) = with(request) {
        getUserResource(id)
            .run { this.resetPassword(request.toCredential()) }
    }

    private fun extractUserId(response: Response) = CreatedResponseUtil.getCreatedId(response)

    private fun getUserResource(userId: String) =
        realmResource.users()
            .get(userId)

    private companion object {

        fun Api.CreateUserRequest.toUserRepresentation(): UserRepresentation {
            return UserRepresentation().also {
                it.username = username
                it.firstName = firstName
                it.lastName = lastName
                it.email = email
                it.isEnabled = true
            }
        }

        fun Api.UpdateUserRequest.toUserRepresentation() =
            UserRepresentation().also {
                it.firstName = firstName
                it.lastName = lastName
                it.email = email
            }

        fun Api.UserUpdatePasswordRequest.toCredential() =
            CredentialRepresentation()
                .apply {
                    isTemporary = false
                    type = CredentialRepresentation.PASSWORD
                    value = password
                }

        fun UserRepresentation.toUpdateUserResponse() =
            Api.UpdateUserResponse(
                id = id,
                firstName = firstName,
                lastName = lastName,
                email = email
            )

        fun UserRepresentation.toUserResponseBody() =
            Api.UserResponseBody(
                id = id,
                username = username,
                firstName = firstName,
                lastName = lastName,
                email = email
            )
    }
}
