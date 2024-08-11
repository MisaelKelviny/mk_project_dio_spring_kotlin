package me.dio.dev_week_2024.controller.exception

import me.dio.dev_week_2024.service.exception.BusinessException
import me.dio.dev_week_2024.service.exception.NotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.slf4j.Logger

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger: Logger? = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(): ResponseEntity<String> {
        return ResponseEntity("Resource ID not found.", HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Throwable::class)
    fun handleUnexpectedException(unexpectedException: Throwable): ResponseEntity<String> {
        val message = "Unexpected server error."
        logger?.error(message, unexpectedException)
        return ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
