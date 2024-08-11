package me.dio.dev_week_2024.controller.dto

import me.dio.dev_week_2024.domain.model.User

data class UserDto(
    val id: Long,
    val name: String,
    val account: AccountDto?,
    val card: CardDto?,
    val features: List<FeatureDto>,
    val news: List<NewsDto>
) {
    constructor(model: User) : this(
        model.id,
        model.name,
        model.account?.let { AccountDto(it) },
        model.card?.let { CardDto(it) },
        model.features?.map { FeatureDto(it) } ?: emptyList(),
        model.news?.map { NewsDto(it) } ?: emptyList()
    )

    fun toModel(): User {
        return User().apply {
            id = this@UserDto.id
            name = this@UserDto.name
            account = this@UserDto.account?.toModel()!!
            card = this@UserDto.card?.toModel()!!
            features = this@UserDto.features.map { it.toModel() }
            news = this@UserDto.news.map { it.toModel() }
        }
    }
}
