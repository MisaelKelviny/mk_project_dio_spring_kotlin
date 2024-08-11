package me.dio.dev_week_2024.controller.dto

import me.dio.dev_week_2024.domain.model.News

data class NewsDto(
    val id: Long,
    val icon: String,
    val description: String
) {
    constructor(model: News) : this(
        model.id,
        model.icon,
        model.description
    )

    fun toModel(): News {
        return News().apply {
            id = this@NewsDto.id
            icon = this@NewsDto.icon
            description = this@NewsDto.description
        }
    }
}