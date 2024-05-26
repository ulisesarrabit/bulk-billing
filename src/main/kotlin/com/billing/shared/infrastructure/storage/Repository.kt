package com.billing.shared.infrastructure.storage

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction


abstract class Repository {
    init {
        val url : String = System.getenv("DATABASE_URL") ?: throw IllegalArgumentException("Database url not provided")
        val user : String = System.getenv("DATABASE_USER") ?: throw IllegalArgumentException("Database user not provided")
        val driver : String = System.getenv("DATABASE_DRIVER") ?: throw IllegalArgumentException("Database driver not provided")
        val password : String = System.getenv("DATABASE_PASSWORD") ?: throw IllegalArgumentException("Database password not provided")

        Database.connect(url = url, user = user, driver = driver,password = password)
        transaction {
            initialize()
        }
    }

    abstract fun initialize()
}