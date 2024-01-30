package com.github.eprudnikov.vertx_sample

import com.github.eprudnikov.vertx_sample.features.call.handlers.CallRequestHandler
import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.ext.web.Router

class MainVerticle : AbstractVerticle() {

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

  fun getRouter(): Router {
    // TODO Introduce DI and extract calls-specific routes into its package
    val callRequestHandler = CallRequestHandler()

    val router = Router.router(vertx)
    router.route("/calls").handler(callRequestHandler::getCalls)
    return router
  }
}
