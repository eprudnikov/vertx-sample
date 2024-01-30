package com.github.eprudnikov.vertx_sample.features.call.handlers

import com.github.eprudnikov.vertx_sample.features.call.models.Call
import io.vertx.core.json.JsonArray
import io.vertx.ext.web.RoutingContext
import java.util.*

class CallRequestHandler {
  val data = listOf(
    Call(UUID.randomUUID()),
    Call(UUID.randomUUID()),
    Call(UUID.randomUUID())
  )

  fun getCalls(context: RoutingContext) {
    context.response()
      .putHeader("Content-Type", "application/json")
      .end(JsonArray.of(data).toBuffer())
  }
}
