package com.github.benjaminjacobberg.ibmmqtool

import org.springframework.stereotype.Component
import javax.jms.ConnectionFactory
import javax.jms.JMSContext
import javax.jms.Queue
import javax.jms.QueueBrowser


@Component
class MessageConsumer : IbmMqConnection() {
    fun scrape(size: Int, connectionInformation: ConnectionInformation): List<Message> {
        val connectionFactory: ConnectionFactory = connectionFactory(connectionInformation)
        val jmsContext: JMSContext = connectionFactory.createContext()
        val destination: Queue = jmsContext.createQueue("queue:///${connectionInformation.queue}?targetClient=1")
        val browser: QueueBrowser = jmsContext.createBrowser(destination)

        val messages: MutableList<Message> = ArrayList()
        val enumeration = browser.enumeration
        val mutableIterator: ArrayList<javax.jms.Message?> = enumeration.toList() as ArrayList<javax.jms.Message?>

        var i: Int = 0
        for (message: javax.jms.Message? in mutableIterator) {
            if (i >= size) {
                break
            }
            val body: String = message?.getBody(String::class.java) ?: continue
            val jmsMessageId: String = message.jmsMessageID
            val header: MessageHeader = MessageHeader(jmsMessageId, "")
            messages.add(Message(header, body))
            i++
        }

        browser.close()
        jmsContext.close()

        return messages
    }

    fun info(connectionInformation: ConnectionInformation): QueueInfo {
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

        return QueueInfo(depth = depth)
    }

}
