package com.github.benjaminjacobberg.ibmmqtool

import org.springframework.stereotype.Component
import java.util.*
import javax.jms.JMSConsumer
import javax.jms.JMSContext
import javax.jms.Queue
import javax.jms.QueueBrowser
import kotlin.collections.ArrayList

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
            messages.add(Message(body))
            i++
        }

        return messages
    }

}
