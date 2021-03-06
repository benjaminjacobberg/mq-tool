package com.github.benjaminjacobberg.mqtool.ibmmq

import com.github.benjaminjacobberg.mqtool.*
import com.github.benjaminjacobberg.mqtool.Message
import com.github.benjaminjacobberg.mqtool.MessageConsumer
import java.util.*
import javax.jms.*
import javax.jms.Queue
import kotlin.collections.ArrayList

open class IbmMqMessageConsumer : MessageConsumer, IbmMqConnectionFactory() {
    override fun scrape(size: Int, connectionInformation: ConnectionInformation): List<Message> {
        val connectionFactory: ConnectionFactory = connectionFactory(connectionInformation)
        val jmsContext: JMSContext = connectionFactory.createContext()
        val destination: Queue = jmsContext.createQueue("queue:///${connectionInformation.queue}?targetClient=1")
        val browser: QueueBrowser = jmsContext.createBrowser(destination)

        val messages: MutableList<Message> = ArrayList()
        val enumeration = browser.enumeration

        var i = 0
        while (enumeration.hasMoreElements() && i < size) {
            val message = enumeration.nextElement() as javax.jms.Message?

            val body: String = message?.getBody(String::class.java) ?: continue
            val header = MessageHeader(message.jmsMessageID, message.jmsCorrelationID, "${Date(message.jmsTimestamp)}")
            messages.add(Message(header, body))
            i++
        }

        browser.close()
        jmsContext.close()

        return messages
    }

    override fun info(connectionInformation: ConnectionInformation): MessageQueueInfoResponse {
        val connectionFactory: ConnectionFactory = connectionFactory(connectionInformation)
        val jmsContext: JMSContext = connectionFactory.createContext()
        val destination: Queue = jmsContext.createQueue("queue:///${connectionInformation.queue}?targetClient=1")
        val browser: QueueBrowser = jmsContext.createBrowser(destination)

        var depth = 0
        val enumeration = browser.enumeration
        while (enumeration.hasMoreElements()) {
            enumeration.nextElement()
            depth++
        }

        browser.close()
        jmsContext.close()

        return MessageQueueInfoResponse(depth = depth)
    }

    override fun pull(jmsId: String, connectionInformation: ConnectionInformation): Message? {
        val connectionFactory: ConnectionFactory = connectionFactory(connectionInformation)
        val jmsContext: JMSContext = connectionFactory.createContext()
        val destination: Queue = jmsContext.createQueue("queue:///${connectionInformation.queue}?targetClient=1")
        val consumer: JMSConsumer = jmsContext.createConsumer(destination, "JMSMessageID='$jmsId'")

        val jmsMessage: javax.jms.Message? = consumer.receiveNoWait()
        val message: Message? = jmsMessage?.let { Message(MessageHeader(it.jmsMessageID, it.jmsCorrelationID, "${Date(it.jmsTimestamp)}"), it.getBody(String::class.java)) }

        consumer.close()
        jmsContext.close()

        return message
    }
}
