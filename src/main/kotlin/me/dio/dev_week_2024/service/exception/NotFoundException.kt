package me.dio.dev_week_2024.service.exception

class NotFoundException : BusinessException("Resource not found.") {
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}