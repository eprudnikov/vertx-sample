package com.github.eprudnikov.vertx_sample.features.call.configuration

import com.github.eprudnikov.vertx_sample.features.call.handlers.CallRequestHandler
import com.github.eprudnikov.vertx_sample.features.call.repositories.CallRepository
import com.github.eprudnikov.vertx_sample.features.call.repositories.PgClientCallRepository
import io.vertx.rxjava3.ext.web.Router
import org.koin.dsl.module

val callModule = module {
  // Persistence
  single<CallRepository> { PgClientCallRepository(get()) }

  // Routers
  single<Pair<String, Router>> {
    val callRequestHandler = CallRequestHandler(get())
    val router = Router.router(get())
    router.route().handler(callRequestHandler::getCalls)
    "/calls/*" to router
  }
}
