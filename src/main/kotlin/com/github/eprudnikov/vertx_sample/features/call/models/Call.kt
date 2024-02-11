package com.github.eprudnikov.vertx_sample.features.call.models

import java.time.Instant
import java.util.*

data class Call(val id: UUID, val tenantId: UUID, val createdAt: Instant, val updatedAt: Instant, val deleted: Boolean)
