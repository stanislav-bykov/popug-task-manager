package org.popug.tracker.user.dal.repository.user

import org.popug.tracker.user.dal.model.user.UserAccount
import org.popug.tracker.user.dal.model.user.UserAccount.AccountStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface UserAccountRepository : JpaRepository<UserAccount, Long> {

    fun findAllByStatus(status: AccountStatus): Collection<UserAccount>

    @Modifying
    @Query("update UserAccount u set u.publicId = :publicId where u.id = :userId")
    fun modifyPublicId(userId: Long, publicId: String)
}
