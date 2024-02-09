package com.github.eprudnikov.vertx_sample.features.call.handlers

import com.github.eprudnikov.vertx_sample.features.call.repositories.CallRepository
import io.vertx.core.json.Json
import io.vertx.rxjava3.ext.web.RoutingContext

class CallRequestHandler(private val callRepository: CallRepository) {
  fun getCalls(context: RoutingContext) {
    val response = context.response()
    response.putHeader("Content-Type", "application/json")
    callRepository.findAll()
      .subscribe({ call ->
        response.write(Json.encode(call))
      }, { error ->
        response.putHeader("Content-Type", "plain/text")
          .setStatusCode(500)
          .end(error.message)
      }, {
        response.end()
      })
  }
}
