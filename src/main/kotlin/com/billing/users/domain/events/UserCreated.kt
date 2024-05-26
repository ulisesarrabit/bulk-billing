package com.billing.users.domain.events

import com.billing.shared.domain.Event
import java.time.LocalDateTime
import java.util.UUID

data class UserCreated(val userId: UUID, override val id: UUID, override val occurredOn: String): Event{
    companion object{
        fun create(userId: UUID, eventId: UUID? = null, occurredOn: String? = null): UserCreated {
            val id = eventId ?: UUID.randomUUID()
            val occurredOnValue: String = occurredOn ?: LocalDateTime.now().toString()

            return UserCreated(userId, id, occurredOnValue)
        }
    }
}