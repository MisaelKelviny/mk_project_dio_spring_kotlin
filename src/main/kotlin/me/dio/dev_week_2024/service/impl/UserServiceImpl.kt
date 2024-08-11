package me.dio.dev_week_2024.service.impl

import jakarta.transaction.Transactional
import me.dio.dev_week_2024.domain.model.User
import me.dio.dev_week_2024.domain.repository.UserRepository
import me.dio.dev_week_2024.service.UserService
import me.dio.dev_week_2024.service.exception.BusinessException
import me.dio.dev_week_2024.service.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository): UserService {
    companion object {
        private const val UNCHANGEABLE_USER_ID: Long = 1L
    }

    @Transactional()
    override fun findAll(): List<User> = userRepository.findAll()

    @Transactional()
    override fun findById(id: Long): User = userRepository.findById(id).orElseThrow { NotFoundException() }

    @Transactional
    override fun create(userToCreate: User): User {
        userToCreate ?: throw BusinessException("User to create must not be null.")
        userToCreate.account ?: throw BusinessException("User account must not be null.")
        userToCreate.card ?: throw BusinessException("User card must not be null.")

        validateChangeableId(userToCreate.id, "created")
        if (userRepository.existsByAccountNumber(userToCreate.account.number)) {
            throw BusinessException("This account number already exists.")
        }
        if (userRepository.existsByCardNumber(userToCreate.card.number)) {
            throw BusinessException("This card number already exists.")
        }
        return userRepository.save(userToCreate)
    }

    @Transactional
    override fun update(id: Long, userToUpdate: User): User {
        validateChangeableId(id, "updated")
        val dbUser = findById(id)
        if (dbUser.id != userToUpdate.id) {
            throw BusinessException("Update IDs must be the same.")
        }

        dbUser.apply {
            name = userToUpdate.name
            account = userToUpdate.account
            card = userToUpdate.card
            features = userToUpdate.features
            news = userToUpdate.news
        }

        return userRepository.save(dbUser)
    }

    @Transactional
    override fun delete(id: Long) {
        validateChangeableId(id, "deleted")
        val dbUser = findById(id)
        userRepository.delete(dbUser)
    }

    private fun validateChangeableId(id: Long, operation: String) {
        if (id == UNCHANGEABLE_USER_ID) {
            throw BusinessException("User with ID $UNCHANGEABLE_USER_ID can not be $operation.")
        }
    }
}