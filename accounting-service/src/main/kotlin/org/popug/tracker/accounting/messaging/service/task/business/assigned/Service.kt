package org.popug.tracker.accounting.messaging.service.task.business.assigned

import org.popug.tracker.accounting.dal.model.account.Account
import org.popug.tracker.accounting.dal.model.task.Task
import org.popug.tracker.accounting.dal.model.transaction.CreditTransaction
import org.popug.tracker.accounting.dal.repository.account.AccountRepository
import org.popug.tracker.accounting.dal.repository.task.TaskRepository
import org.popug.tracker.accounting.dal.repository.transaction.TransactionRepository
import org.popug.tracker.core.business.BusinessService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Service(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository,
    private val taskRepository: TaskRepository
) : BusinessService<Api.Request, Unit> {

    @Transactional
    override fun invoke(request: Api.Request) {
        with(request) {
            createTransaction(
                account = findOrCreateAccount(userPublicId = userPublicId),
                task = findOrCreateTask(taskPublicId = taskPublicId, userPublicId = userPublicId)
            )
        }
    }

    private fun findOrCreateAccount(userPublicId: String) =
        accountRepository.findByPublicId(userPublicId)
            .orElseGet { createAccount(userPublicId = userPublicId) }

    private fun findOrCreateTask(taskPublicId: String, userPublicId: String) =
        // TODO: 20.05.2022 if exists -> check task on task.publicUserId == userPublicId
        taskRepository.findByPublicId(taskPublicId)
            .orElseGet { createTask(taskPublicId = taskPublicId, userPublicId = userPublicId) }

    private fun createTransaction(account: Account, task: Task) =
        transactionRepository.save(
            CreditTransaction(
                account = account.also {
                    it.walletAmount -= task.penaltyRate
                },
                task = task,
                credit = task.penaltyRate
            )
        )

    private fun createAccount(userPublicId: String) =
        accountRepository.save(Account(publicId = userPublicId))

    private fun createTask(taskPublicId: String, userPublicId: String) =
        taskRepository.save(Task(publicId = taskPublicId, userPublicId = userPublicId))

    private companion object
}
