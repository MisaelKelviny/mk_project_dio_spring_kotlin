package me.dio.dev_week_2024.controller.dto

import me.dio.dev_week_2024.domain.model.Feature

data class FeatureDto(
    val id: Long,
    val icon: String,
    val description: String
) {
    constructor(model: Feature) : this(
        model.id,
        model.icon,
        model.description
    )

    fun toModel(): Feature {
        return Feature().apply {
            id = this@FeatureDto.id
            icon = this@FeatureDto.icon
            description = this@FeatureDto.description
        }
    }
}
