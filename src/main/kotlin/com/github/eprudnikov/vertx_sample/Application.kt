package com.github.eprudnikov.vertx_sample

import com.github.eprudnikov.vertx_sample.configuration.persistenceModule
import com.github.eprudnikov.vertx_sample.features.call.configuration.callModule
import io.vertx.core.Vertx
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.fileProperties

fun main() {
  val vertx = Vertx.vertx()
  startKoin {
    printLogger(Level.INFO) // Enable logging to see what's happening
    fileProperties()
    modules(
      module {
        single<Vertx> { vertx }
      },
      persistenceModule,
      callModule
    )
  }

  vertx.deployVerticle(MainVerticle())
}
