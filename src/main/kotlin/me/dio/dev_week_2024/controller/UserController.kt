package me.dio.dev_week_2024.controller

import io.swagger.v3.oas.annotations.Operation
import me.dio.dev_week_2024.controller.dto.UserDto
import me.dio.dev_week_2024.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@CrossOrigin
@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users")
    fun findAll(): ResponseEntity<List<UserDto>> {
        val users = userService.findAll()
        val usersDto = users.map { UserDto(it) }
        return ResponseEntity.ok(usersDto)
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID", description = "Retrieve a specific user based on its ID")
    fun findById(@PathVariable id: Long): ResponseEntity<UserDto> {
        val user = userService.findById(id)
        return ResponseEntity.ok(UserDto(user))
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user and return the created user's data")
    fun create(@RequestBody userDto: UserDto): ResponseEntity<UserDto> {
        val user = userService.create(userDto.toModel())
        val location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(user.id)
            .toUri()
        return ResponseEntity.created(location).body(UserDto(user))
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "Update the data of an existing user based on its ID")
    fun update(@PathVariable id: Long, @RequestBody userDto: UserDto): ResponseEntity<UserDto> {
        val user = userService.update(id, userDto.toModel())
        return ResponseEntity.ok(UserDto(user))
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Delete an existing user based on its ID")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        userService.delete(id)
        return ResponseEntity.noContent().build()
    }
}