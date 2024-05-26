package com.billing.users.infrastructure.api

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureUsersRouting() {
    routing {
        post("/users") {
            val user = call.receive<CreateUserRequest>()
            CreateController().handle(user)
            call.respond(HttpStatusCode.Created)
        }
    }
}