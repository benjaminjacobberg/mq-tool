package com.github.benjaminjacobberg.mqtool

data class SendMessageDTO(val host: String,
                          val port: Int,
                          val channel: String,
                          val qm: String,
                          val userId: String,
                          val password: String?,
                          val queue: String,
                          val body: String,
                          val implementation: Implementation = Implementation.IBM_MQ)
