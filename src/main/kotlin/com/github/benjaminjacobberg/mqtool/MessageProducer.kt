package com.github.benjaminjacobberg.mqtool

import org.springframework.stereotype.Component
import javax.jms.ConnectionFactory
import javax.jms.JMSContext
import javax.jms.JMSProducer
import javax.jms.Queue

@Component
class MessageProducer : MqConnection() {
    fun put(message: Message, connectionInformation: ConnectionInformation) {
        val connectionFactory: ConnectionFactory = connectionFactory(connectionInformation)
        val jmsContext: JMSContext = connectionFactory.createContext()
        val destination: Queue = jmsContext.createQueue("queue:///${connectionInformation.queue}?targetClient=1")
        val producer: JMSProducer = jmsContext.createProducer()
        producer.send(destination, message.body)
    }
}
