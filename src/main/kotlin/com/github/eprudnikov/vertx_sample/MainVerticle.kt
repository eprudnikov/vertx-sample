package com.github.eprudnikov.vertx_sample

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.ext.web.Router
import org.koin.core.component.KoinComponent

class MainVerticle : AbstractVerticle(), KoinComponent {

  override fun start(startPromise: Promise<Void>) {
    vertx
      .createHttpServer()
      .requestHandler(getRouter())
      .listen(8888) { http ->
        if (http.succeeded()) {
          startPromise.complete()
          println("HTTP server started on port 8888")
        } else {
          startPromise.fail(http.cause())
        }
      }
  }

  private fun getRouter(): Router {
    val subRoutes = getKoin().getAll<Pair<String, Router>>()

    val mainRouter = Router.router(vertx)
    subRoutes.forEach { urlToRouter ->
      println("Register router to ${urlToRouter.first}")
      mainRouter.route(urlToRouter.first).subRouter(urlToRouter.second)
    }
    return mainRouter
  }
}
