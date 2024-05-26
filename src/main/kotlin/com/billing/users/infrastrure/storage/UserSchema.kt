package com.billing.users.infrastrure.storage

import org.jetbrains.exposed.sql.Table

class UserSchema {
    object Users : Table() {
        val id = uuid("id")
        val name = varchar("name", length = 50)
        val lastname = varchar("lastname", length = 50).nullable()
        val email = varchar("email", length = 50)
        val password = varchar("password", length = 50)

        override val primaryKey = PrimaryKey(id)
    }
}