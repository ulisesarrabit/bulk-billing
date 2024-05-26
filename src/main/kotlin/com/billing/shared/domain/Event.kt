package com.billing.shared.domain

import java.util.UUID

interface Event {
    val id: UUID
    val occurredOn: String
}