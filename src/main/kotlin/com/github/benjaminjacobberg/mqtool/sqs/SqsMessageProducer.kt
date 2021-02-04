package com.github.benjaminjacobberg.mqtool.sqs

import com.github.benjaminjacobberg.mqtool.ConnectionInformation
import com.github.benjaminjacobberg.mqtool.Message
import com.github.benjaminjacobberg.mqtool.MessageProducer
import javax.jms.Session

open class SqsMessageProducer : MessageProducer, SqsConnectionFactory() {
    override fun put(message: Message, connectionInformation: ConnectionInformation) {
        val connection = connectionFactory(connectionInformation).createConnection()
        val session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE)
        val producer = session.createProducer(session.createQueue(connectionInformation.queue))
        producer.send(session.createTextMessage(message.body))
        session.close()
        connection.close()
    }
}