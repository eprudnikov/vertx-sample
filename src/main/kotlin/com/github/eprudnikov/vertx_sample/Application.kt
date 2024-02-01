package com.github.eprudnikov.vertx_sample

import io.vertx.core.AsyncResult
import io.vertx.pgclient.PgBuilder
import io.vertx.pgclient.PgConnectOptions
import io.vertx.sqlclient.PoolOptions
import io.vertx.sqlclient.Row
import io.vertx.sqlclient.RowSet


fun main () {
  val connectOptions = PgConnectOptions()
    .setPort(5432)
    .setHost("localhost")
    .setDatabase("XXX")
    .setUser("postgres")
    .setPassword("secret")

// Pool options

// Pool options
  val poolOptions: PoolOptions = PoolOptions()
    .setMaxSize(5)

// Create the client pool

// Create the client pool
  val client = PgBuilder
    .client()
    .with(poolOptions)
    .connectingTo(connectOptions)
    .build()

// A simple query

// A simple query
  client
    .query("SELECT * FROM call")
    .execute()
    .onComplete { ar: AsyncResult<RowSet<Row?>> ->
      if (ar.succeeded()) {
        val result: RowSet<Row?> = ar.result()
        System.out.println("Got " + result.size() + " rows ")
      } else {
        println("Failure: " + ar.cause().message)
      }

      // Now close the pool
      client.close()
    }
}
