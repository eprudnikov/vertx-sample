package com.github.eprudnikov.vertx_sample.features.call.repositories

import com.github.eprudnikov.vertx_sample.features.call.models.Call
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.sqlclient.Pool
import io.vertx.sqlclient.Row
import java.util.*

class PgClientCallRepository(val pool: Pool) : CallRepository {

  override fun findAll(): Future<List<Call>> {
    val promise = Promise.promise<List<Call>>()

    pool.query("SELECT id::text FROM call LIMIT 10")
      .execute()
      .onSuccess { ar ->
        promise.complete(ar.map(this::rowToCall))
      }
      .onFailure { throwable ->
        promise.fail(throwable)
      }
    return promise.future();
  }

  private fun rowToCall(row: Row): Call {
    val idAsString = row.getString("id")
    return Call(UUID.fromString(idAsString))
  }
}
