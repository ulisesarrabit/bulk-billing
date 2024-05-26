package com.billing.users.infrastructure.api

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(val id: String, val name: String, val lastname: String?, val email: String, val password: String)

class CreateController{
    fun handle(params: CreateUserRequest?): Unit {
        if (params == null) {
            throw IllegalArgumentException("Invalid params")
        }

    }
}


