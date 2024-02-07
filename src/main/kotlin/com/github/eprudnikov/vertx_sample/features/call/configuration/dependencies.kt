package com.github.eprudnikov.vertx_sample.features.call.configuration

import com.github.eprudnikov.vertx_sample.features.call.repositories.CallRepository
import com.github.eprudnikov.vertx_sample.features.call.repositories.PgClientCallRepository
import org.koin.dsl.module

val callModule = module {
  single<CallRepository> { PgClientCallRepository(get()) }
}
