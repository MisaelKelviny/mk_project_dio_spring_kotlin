package me.dio.dev_week_2024.controller.dto

import me.dio.dev_week_2024.domain.model.Account
import java.math.BigDecimal

data class AccountDto(
    val id: Long,
    val number: String,
    val agency: String,
    val balance: BigDecimal,
    val limit: BigDecimal
) {
    constructor(model: Account) : this(
        model.id,
        model.number,
        model.agency,
        model.balance,
        model.limit
    )

    fun toModel(): Account {
        return Account().apply {
            id = this@AccountDto.id
            number = this@AccountDto.number
            agency = this@AccountDto.agency
            balance = this@AccountDto.balance
            limit = this@AccountDto.limit
        }
    }
}