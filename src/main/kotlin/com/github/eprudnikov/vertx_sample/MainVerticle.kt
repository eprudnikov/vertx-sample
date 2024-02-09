package com.github.eprudnikov.vertx_sample

import io.reactivex.rxjava3.core.Completable
import io.vertx.rxjava3.ext.web.Router
import io.vertx.rxjava3.core.AbstractVerticle
import org.koin.core.component.KoinComponent

class MainVerticle : AbstractVerticle(), KoinComponent {

  override fun rxStart(): Completable {
    return vertx.createHttpServer()
      .requestHandler(getRouter())
      .rxListen(8888)
      .ignoreElement()
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
