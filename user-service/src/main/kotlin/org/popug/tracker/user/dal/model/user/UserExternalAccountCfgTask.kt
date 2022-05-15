package org.popug.tracker.user.dal.model.user

import org.popug.tracker.user.common.dal.Auditable
import javax.persistence.*

@Entity
data class UserExternalAccountCfgTask(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @OneToOne(fetch = FetchType.LAZY) val userAccount: UserAccount,
    @Enumerated(EnumType.STRING)
    var cfgStep: CfgTaskStep = CfgTaskStep.REGISTRATION
) : Auditable() {

    enum class CfgTaskStep {
        REGISTRATION {
            override fun nextTaskStep() = ROLE_ASSIGN
        },
        ROLE_ASSIGN {
            override fun nextTaskStep() = CREDENTIAL_REFRESHING
        },
        CREDENTIAL_REFRESHING {
            override fun nextTaskStep() = CONFIGURED
        },
        CONFIGURED {
            override fun nextTaskStep() = CONFIGURED
        };

        abstract fun nextTaskStep(): CfgTaskStep
    }

    fun completeCfxStep() {
        cfgStep = cfgStep.nextTaskStep()
    }
}
