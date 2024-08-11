package me.dio.dev_week_2024.service.exception

open class BusinessException(message: String) : RuntimeException(message) {
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}