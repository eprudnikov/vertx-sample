package com.github.eprudnikov.vertx_sample

import com.github.eprudnikov.vertx_sample.configuration.persistenceModule
import io.vertx.core.AsyncResult
import io.vertx.sqlclient.Row
import io.vertx.sqlclient.RowSet
import io.vertx.sqlclient.SqlClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.fileProperties

class SqlClientDemo: KoinComponent {
   private val client: SqlClient = get()

  init {
    client
      .query("SELECT * FROM call")
      .execute()
      .onComplete { ar: AsyncResult<RowSet<Row?>> ->
        if (ar.succeeded()) {
          val result: RowSet<Row?> = ar.result()
          System.out.println("Got " + result.size() + " calls ")
        } else {
          println("Failure: " + ar.cause().message)
        }

        // Now close the pool
        client.close()
      }
  }
}

fun main() {
  startKoin {
    printLogger(Level.INFO) // Enable logging to see what's happening
    fileProperties()
    modules(persistenceModule)
  }

  SqlClientDemo()
}
