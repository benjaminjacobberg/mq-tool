package com.github.benjaminjacobberg.mqtool

data class ConnectionInformation(val host: String,
                                 val port: Int,
                                 val channel: String,
                                 val qm: String,
                                 val userId: String,
                                 val password: String?,
                                 val queue: String,
                                 val implementation: Implementation = Implementation.IBM_MQ)

enum class Implementation {
    IBM_MQ, SQS
}