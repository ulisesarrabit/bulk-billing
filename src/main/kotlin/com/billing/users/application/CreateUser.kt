package com.billing.users.application

import com.billing.users.domain.User
import com.billing.users.domain.UserRepository
import java.util.*

data class CreateUserParams(val id: String, val name: String, val lastname: String?, val email: String, val password: String)

class CreateUser(private val userRepository: UserRepository) {
    fun handle(params: CreateUserParams): Unit {
        val user = User.create(UUID.fromString(params.id), params.name, params.lastname, params.email, params.password)
        userRepository.create(user)
    }
}