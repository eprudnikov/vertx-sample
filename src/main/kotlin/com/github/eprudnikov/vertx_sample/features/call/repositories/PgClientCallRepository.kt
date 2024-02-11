package com.github.eprudnikov.vertx_sample.features.call.repositories

import com.github.eprudnikov.vertx_sample.features.call.models.Call
import io.reactivex.rxjava3.core.Flowable
import io.vertx.rxjava3.sqlclient.Pool
import io.vertx.rxjava3.sqlclient.Row
import java.time.ZoneOffset
import java.util.*

class PgClientCallRepository(val pool: Pool) : CallRepository {

  override fun findAll(): Flowable<Call> {
    // TODO create a prepared statement
    return pool.query("SELECT id, tenant_id, created_at, updated_at, is_deleted FROM call LIMIT 10")
      .rxExecute()
      .flatMapPublisher { rowSet -> Flowable.fromIterable(rowSet)}
      .map(::rowToCall)
  }

  private fun rowToCall(row: Row): Call {
    return Call(
      row.get(UUID::class.java, "id"),
      row.get(UUID::class.java, "tenant_id"),
      row.getLocalDateTime("created_at").toInstant(ZoneOffset.UTC),
      row.getLocalDateTime("updated_at").toInstant(ZoneOffset.UTC),
      row.getBoolean("is_deleted")
    )
  }
}
