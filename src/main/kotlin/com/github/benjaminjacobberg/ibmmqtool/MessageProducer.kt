package com.github.benjaminjacobberg.ibmmqtool

import org.springframework.stereotype.Component
import javax.jms.JMSProducer
import javax.jms.Queue


@Component
class MessageProducer : IbmMqConnection() {
    fun put(message: Message, connectionInformation: ConnectionInformation) {
        val (context, destination: Queue) = queueConnection(connectionInformation)
        val producer: JMSProducer = context.createProducer()
        producer.send(destination, message.body)
    }
}
