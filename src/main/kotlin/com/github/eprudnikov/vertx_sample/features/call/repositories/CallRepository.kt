package com.github.eprudnikov.vertx_sample.features.call.repositories

import com.github.eprudnikov.vertx_sample.features.call.models.Call
import io.reactivex.rxjava3.core.Flowable

interface CallRepository {
  fun findAll(): Flowable<Call>
}
