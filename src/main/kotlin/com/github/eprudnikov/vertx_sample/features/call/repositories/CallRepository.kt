package com.github.eprudnikov.vertx_sample.features.call.repositories

import com.github.eprudnikov.vertx_sample.features.call.models.Call
import io.vertx.core.Future

interface CallRepository {
  fun findAll(): Future<List<Call>>
}
