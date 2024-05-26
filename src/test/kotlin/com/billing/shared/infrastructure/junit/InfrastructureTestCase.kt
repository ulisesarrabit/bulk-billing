package com.billing.shared.infrastructure.junit

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before

abstract class InfrastructureTestCase {
    @Before
    fun setup() {
        Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver")

        transaction {
            initialize()
        }
    }

    @After
    fun teardown() {
        transaction {
            cleanup()
        }
    }

    protected abstract fun initialize()
    protected abstract fun cleanup()
}