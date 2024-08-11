package me.dio.dev_week_2024.service

interface CrudService<ID, T> {
    fun findAll(): List<T>
    fun findById(id: ID): T
    fun create(entity: T): T
    fun update(id: ID, entity: T): T
    fun delete(id: ID)
}