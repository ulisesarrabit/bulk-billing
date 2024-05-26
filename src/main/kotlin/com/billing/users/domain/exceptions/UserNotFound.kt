package com.billing.users.domain.exceptions

class UserNotFound: Exception() {
    override val message: String = "User not found"
}