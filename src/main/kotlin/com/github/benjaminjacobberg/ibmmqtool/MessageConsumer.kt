package com.github.benjaminjacobberg.ibmmqtool

import com.ibm.msg.client.jms.internal.JmsConsumerImpl
import org.springframework.stereotype.Component
import javax.jms.JMSContext
import javax.jms.Queue

@Component
class MessageConsumer : IbmMqConnection() {
    fun scrape(size: Int, connectionInformation: ConnectionInformation): List<String> {
        val (context: JMSContext, queue: Queue) = queueConnection(connectionInformation)
        val consumer: JmsConsumerImpl = context.createConsumer(queue) as JmsConsumerImpl

        TODO("Need to take a small peek at what's on the queue without removing any messages.")
    }

}
