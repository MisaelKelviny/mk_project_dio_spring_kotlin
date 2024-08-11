package me.dio.dev_week_2024.domain.repository
import me.dio.dev_week_2024.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun existsByAccountNumber(number:String): Boolean
    fun existsByCardNumber(number:String): Boolean
}