package com.github.eprudnikov.vertx_sample.configuration

import io.vertx.pgclient.PgBuilder
import io.vertx.pgclient.PgConnectOptions
import io.vertx.sqlclient.PoolOptions
import io.vertx.sqlclient.SqlClient
import io.vertx.sqlclient.SqlConnectOptions
import org.koin.dsl.module

val persistenceModule = module {
  single<SqlConnectOptions> {
    PgConnectOptions()
      .setHost(getProperty("persistence.host"))
      .setPort(Integer.parseInt(getProperty("persistence.port")))
      .setDatabase(getProperty("persistence.database"))
      .setUser(getProperty("persistence.user"))
      .setPassword(getProperty("persistence.password"))
  }
  single { PoolOptions().setMaxSize(5) }
  single<SqlClient> {
    PgBuilder
      .client()
      .with(get())
      .connectingTo(get<SqlConnectOptions>())
      .build()
  }
}
