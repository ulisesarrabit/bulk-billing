package com.billing.users.infrastrure.storage

import com.billing.shared.infrastructure.storage.Repository
import com.billing.users.domain.User
import com.billing.users.domain.UserRepository
import com.billing.users.domain.exceptions.UserNotFound
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID


class PostgresUserRepository: UserRepository, Repository() {
    override fun initialize(): Unit {
        SchemaUtils.create(UserSchema.Users)
    }
    override fun create(user: User): Unit {
        transaction {
            UserSchema.Users.insert {
                it[id] = user.id
                it[name] = user.name
                it[lastname] = user.lastname
                it[email] = user.email
                it[password] = user.password
            }
        }
    }
    override fun findById(id: UUID): User {
        try {
            return transaction {
                val user: User = UserSchema.Users.select { UserSchema.Users.id eq id }
                    .map {
                        User(
                            it[UserSchema.Users.id],
                            it[UserSchema.Users.name],
                            it[UserSchema.Users.lastname],
                            it[UserSchema.Users.email],
                            it[UserSchema.Users.password]
                        )
                    }
                    .single()
                user
            }
        } catch (e: NoSuchElementException) {
            throw UserNotFound()
        }
    }

}