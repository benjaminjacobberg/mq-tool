package com.github.benjaminjacobberg.ibmmqtool

data class ConnectionInformation(val host: String,
                                 val port: Int,
                                 val channel: String,
                                 val qm: String,
                                 val userId: String,
                                 val password: String,
                                 val queue: String)