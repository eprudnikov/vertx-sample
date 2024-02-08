package com.github.eprudnikov.vertx_sample.features.call.handlers

import com.github.eprudnikov.vertx_sample.features.call.repositories.CallRepository
import io.vertx.core.json.Json
import io.vertx.ext.web.RoutingContext

class CallRequestHandler(private val callRepository: CallRepository) {
  fun getCalls(context: RoutingContext) {
    callRepository.findAll()
      .onSuccess { calls ->
        context.response()
          .putHeader("Content-Type", "application/json")
          .end(Json.encode(calls))
      }
      .onFailure { throwable ->
        context.response()
          .putHeader("Content-Type", "plain/text")
          .setStatusCode(500)
          .end(throwable.message)
      }
  }
}
