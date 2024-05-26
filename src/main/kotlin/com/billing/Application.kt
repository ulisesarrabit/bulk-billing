package com.billing

import com.billing.plugins.*
import com.billing.users.infrastructure.api.configureUsersRouting
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureHTTP()
    configureSerialization()
    configureRouting()
    configureUsersRouting()
}
