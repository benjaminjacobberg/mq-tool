package com.github.benjaminjacobberg.ibmmqtool

import org.springframework.stereotype.Component
import javax.jms.JMSContext
import javax.jms.Queue
import javax.jms.QueueBrowser


@Component
class MessageConsumer : IbmMqConnection() {
    fun scrape(size: Int, connectionInformation: ConnectionInformation): List<Message> {
        val (context: JMSContext, queue: Queue) = queueConnection(connectionInformation)
        val browser: QueueBrowser = context.createBrowser(queue)

        val messages: MutableList<Message> = ArrayList()
        val mutableIterator: MutableIterator<javax.jms.Message?> = browser.enumeration.asIterator() as MutableIterator<javax.jms.Message?>

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

        return messages
    }

    fun info(connectionInformation: ConnectionInformation): QueueInfo {
        val (context: JMSContext, queue: Queue) = queueConnection(connectionInformation)
        val browser: QueueBrowser = context.createBrowser(queue)

        var depth = 0
        val enumeration = browser.enumeration
        while (enumeration.hasMoreElements()) {
            enumeration.nextElement()
            depth++
        }

        browser.close()

        return QueueInfo(depth = depth)
    }

}
