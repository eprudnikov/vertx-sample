package com.github.eprudnikov.vertx_sample

import com.github.eprudnikov.vertx_sample.configuration.persistenceModule
import com.github.eprudnikov.vertx_sample.features.call.configuration.callModule
import io.reactivex.rxjava3.core.Completable
import io.vertx.rxjava3.core.Vertx
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.fileProperties

@ExtendWith(VertxExtension::class)
class MainVerticleTest {

  @BeforeEach
  fun deploy_verticle(vertx: Vertx, testContext: VertxTestContext) {
    // It's okay so far to define exactly the same Koan module (with the same persistent module).
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

    vertx.rxDeployVerticle(MainVerticle())
      .flatMapCompletable { Completable.complete() }
      .subscribe(
        { testContext.completeNow() },
        { error -> error.printStackTrace() }
      )
  }

  @Test
  fun verticle_deployed(vertx: Vertx, testContext: VertxTestContext) {
    testContext.completeNow()
  }
}
