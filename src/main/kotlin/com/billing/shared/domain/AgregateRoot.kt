package com.billing.shared.domain

abstract class AgregateRoot {
    private val event_record = mutableListOf<Event>()

    fun record(event: Event): Unit {
        event_record.plus(event)
    }
}