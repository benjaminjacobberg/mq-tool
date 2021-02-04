package com.github.benjaminjacobberg.mqtool

interface MessageProducer {
    fun put(message: Message, connectionInformation: ConnectionInformation)
}