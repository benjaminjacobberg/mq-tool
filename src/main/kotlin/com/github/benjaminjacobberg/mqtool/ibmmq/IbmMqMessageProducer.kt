package com.github.benjaminjacobberg.mqtool.ibmmq

import com.github.benjaminjacobberg.mqtool.ConnectionInformation
import com.github.benjaminjacobberg.mqtool.Message
import com.github.benjaminjacobberg.mqtool.MessageProducer
import javax.jms.ConnectionFactory
import javax.jms.JMSContext
import javax.jms.JMSProducer
import javax.jms.Queue

open class IbmMqMessageProducer : MessageProducer, IbmMqConnectionFactory() {
    override fun put(message: Message, connectionInformation: ConnectionInformation) {
        val connectionFactory: ConnectionFactory = connectionFactory(connectionInformation)
        val jmsContext: JMSContext = connectionFactory.createContext()
        val destination: Queue = jmsContext.createQueue("queue:///${connectionInformation.queue}?targetClient=1")
        val producer: JMSProducer = jmsContext.createProducer()
        producer.send(destination, message.body)
    }
}
