package com.github.eprudnikov.vertx_sample

import com.github.eprudnikov.vertx_sample.configuration.persistenceModule
import com.github.eprudnikov.vertx_sample.features.call.configuration.callModule
import com.github.eprudnikov.vertx_sample.features.call.repositories.CallRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.fileProperties

class SqlClientDemo : KoinComponent {
  private val callRepository: CallRepository = get()

  init {
    callRepository.findAll().onComplete { ar ->
      if (ar.succeeded()) {
        ar.result().forEach { c ->
          println(c)
        }
      } else if (ar.failed()) {
        println("Failure! " + ar.cause().message)
      }
    }
  }
}

fun main() {
  startKoin {
    printLogger(Level.INFO) // Enable logging to see what's happening
    fileProperties()
    modules(
      persistenceModule,
      callModule
    )
  }

  SqlClientDemo()
}
