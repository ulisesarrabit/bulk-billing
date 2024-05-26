package com.billing.users.domain

import java.util.UUID

interface UserRepository {
     fun create(user: User): Unit
     fun findById(id: UUID): User
}