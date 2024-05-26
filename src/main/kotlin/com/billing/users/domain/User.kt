package com.billing.users.domain

import com.billing.shared.domain.AgregateRoot
import com.billing.users.domain.events.UserCreated
import java.util.UUID

data class User(val id: UUID, val name: String, val lastname: String?, val email: String, val password: String):
    AgregateRoot() {
    companion object{
        fun create(id: UUID, name: String, lastname: String?, email: String, password: String): User {
            val user =  User(id, name, lastname, email, password)
            user.record(UserCreated.create(id))
            return user
        }
    }
}