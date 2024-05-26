package com.billing.users.infrastructure.storage

import com.billing.shared.infrastructure.junit.InfrastructureTestCase
import com.billing.users.domain.User
import com.billing.users.domain.exceptions.UserNotFound
import com.billing.users.infrastrure.storage.PostgresUserRepository
import com.billing.users.infrastrure.storage.UserSchema
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PostgresUserRepositoryTest: InfrastructureTestCase() {
    private val userRepository = PostgresUserRepository()

    override fun initialize() {
        SchemaUtils.create(UserSchema.Users)
    }

    override fun cleanup() {
        SchemaUtils.drop(UserSchema.Users)
    }

    @Test
    fun testCreateUser() {
        val user = User(UUID.randomUUID(), "John", "Doe", "john.doe@example.com", "password")
        userRepository.create(user)

        val retrievedUser = userRepository.findById(user.id)
        assertEquals(user, retrievedUser)
    }

    @Test
    fun testFindUserById() {
        val user = User(UUID.randomUUID(), "Jane", "Doe", "jane.doe@example.com", "password")
        transaction {
            userRepository.create(user)
        }

        transaction {
            val retrievedUser = userRepository.findById(user.id)
            assertEquals(user, retrievedUser)
        }
    }

    @Test
    fun testUserNotFound() {
        val nonExistentUserId = UUID.randomUUID()
        transaction {
            assertFailsWith<UserNotFound> {
                userRepository.findById(nonExistentUserId)
            }
        }
    }
}