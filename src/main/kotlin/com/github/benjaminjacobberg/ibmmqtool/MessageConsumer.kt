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

        val mutableIterator: MutableIterator<javax.jms.Message?> = browser.enumeration.asIterator() as MutableIterator<javax.jms.Message?>
        val messages: MutableList<Message> = ArrayList()

        var i: Int = 0
        for (m: javax.jms.Message? in mutableIterator) {
            if (i >= size) {
                break
            }
            val body: String = m?.getBody(String::class.java) ?: continue
            val jmsMessageId: String = m.jmsMessageID
            val header: MessageHeader = MessageHeader(jmsMessageId, "")
            val message: Message = Message(header, body)
            messages.add(message)
            i++
        }

        return messages
    }

}
