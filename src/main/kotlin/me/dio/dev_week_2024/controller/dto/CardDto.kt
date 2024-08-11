package me.dio.dev_week_2024.controller.dto

import me.dio.dev_week_2024.domain.model.Card
import java.math.BigDecimal

data class CardDto(
    val id: Long,
    val number: String,
    val limit: BigDecimal
) {
    constructor(model: Card) : this(
        model.id,
        model.number,
        model.limit
    )

    fun toModel(): Card {
        return Card().apply {
            id = this@CardDto.id
            number = this@CardDto.number
            limit = this@CardDto.limit
        }
    }
}
