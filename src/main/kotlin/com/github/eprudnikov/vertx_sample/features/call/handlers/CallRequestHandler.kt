package com.github.eprudnikov.vertx_sample.features.call.handlers

import com.github.eprudnikov.vertx_sample.features.call.repositories.CallRepository
import io.vertx.core.json.Json
import io.vertx.rxjava3.core.buffer.Buffer
import io.vertx.rxjava3.ext.web.RoutingContext

class CallRequestHandler(private val callRepository: CallRepository) {
  fun getCalls(context: RoutingContext) {
    val response = context.response()
    response.setChunked(true)
    response.putHeader("Content-Type", "application/json")
    callRepository.findAll()
      .map { call -> Buffer.buffer(Json.encode(call)) }
      .subscribe(response.toSubscriber())
  }
}
